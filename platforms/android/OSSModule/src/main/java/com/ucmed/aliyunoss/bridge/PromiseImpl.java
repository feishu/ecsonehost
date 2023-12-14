package com.ucmed.aliyunoss.bridge;

import com.taobao.weex.bridge.JSCallback;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.ArrayMap;

/*
 * Implementation of {@link Promise} that represents a JavaScript Promise which can be passed to the
 * native module as a method parameter.
 *
 * Methods annotated with {@link ReactMethod} that use a {@link Promise} as the last parameter
 * will be marked as "promise" and will return a promise when invoked from JavaScript.
 */
public class PromiseImpl implements Promise {
  // Number of stack frames to parse and return to mReject.invoke
  // for ERROR_MAP_KEY_NATIVE_STACK
  private static final int ERROR_STACK_FRAME_LIMIT = 50;

  private static final String ERROR_DEFAULT_CODE = "EUNSPECIFIED";
  private static final String ERROR_DEFAULT_MESSAGE = "Error not specified.";

  // Keys for mReject's WritableMap
  private static final String ERROR_MAP_KEY_CODE = "code";
  private static final String ERROR_MAP_KEY_MESSAGE = "message";
  private static final String ERROR_MAP_KEY_USER_INFO = "userInfo";
  private static final String ERROR_MAP_KEY_NATIVE_STACK = "nativeStackAndroid";

  // Keys for ERROR_MAP_KEY_NATIVE_STACK's StackFrame maps
  private static final String STACK_FRAME_KEY_CLASS = "class";
  private static final String STACK_FRAME_KEY_FILE = "file";
  private static final String STACK_FRAME_KEY_LINE_NUMBER = "lineNumber";
  private static final String STACK_FRAME_KEY_METHOD_NAME = "methodName";

  private @Nullable JSCallback mResolve;
  private @Nullable JSCallback mReject;

  public PromiseImpl(@Nullable JSCallback resolve, @Nullable JSCallback reject) {
    mResolve = resolve;
    mReject = reject;
  }

  /**
   * Successfully resolve the Promise with an optional value.
   *
   * @param value Object
   */
  @Override
  public void resolve(Object value) {
    if (mResolve != null) {
      mResolve.invoke(value);
      mResolve = null;
      mReject = null;
    }
  }

  /**
   * Report an error without an exception using a custom code and error message.
   *
   * @param code String
   * @param message String
   */
  @Override
  public void reject(String code, String message) {
    reject(code, message, /*Throwable*/ null, /*WritableMap*/ null);
  }

  /**
   * Report an exception with a custom code.
   *
   * @param code String
   * @param throwable Throwable
   */
  @Override
  public void reject(String code, Throwable throwable) {
    reject(code, /*Message*/ null, throwable, /*WritableMap*/ null);
  }

  /**
   * Report an exception with a custom code and error message.
   *
   * @param code String
   * @param message String
   * @param throwable Throwable
   */
  @Override
  public void reject(String code, String message, Throwable throwable) {
    reject(code, message, throwable, /*WritableMap*/ null);
  }

  /**
   * Report an exception, with default error code. Useful in catch-all scenarios where it's unclear
   * why the error occurred.
   *
   * @param throwable Throwable
   */
  @Override
  public void reject(Throwable throwable) {
    reject(/*Code*/ null, /*Message*/ null, throwable, /*WritableMap*/ null);
  }

  /* ---------------------------
   *  With userInfo WritableMap
   * --------------------------- */

  /**
   * Report an exception, with default error code, with userInfo. Useful in catch-all scenarios
   * where it's unclear why the error occurred.
   *
   * @param throwable Throwable
   * @param userInfo WritableMap
   */
  @Override
  public void reject(Throwable throwable, HashMap userInfo) {
    reject(/*Code*/ null, /*Message*/ null, throwable, userInfo);
  }

  /**
   * Reject with a code and userInfo WritableMap.
   *
   * @param code String
   * @param userInfo WritableMap
   */
  @Override
  public void reject(String code, @NonNull HashMap userInfo) {
    reject(code, /*Message*/ null, /*Throwable*/ null, userInfo);
  }

  /**
   * Report an exception with a custom code and userInfo.
   *
   * @param code String
   * @param throwable Throwable
   * @param userInfo WritableMap
   */
  @Override
  public void reject(String code, Throwable throwable, HashMap userInfo) {
    reject(code, /*Message*/ null, throwable, userInfo);
  }

  /**
   * Report an error with a custom code, error message and userInfo, an error not caused by an
   * exception.
   *
   * @param code String
   * @param message String
   * @param userInfo WritableMap
   */
  @Override
  public void reject(String code, String message, @NonNull HashMap userInfo) {
    reject(code, message, /*Throwable*/ null, userInfo);
  }

  /**
   * Report an exception with a custom code, error message and userInfo.
   *
   * @param code String
   * @param message String
   * @param throwable Throwable
   * @param userInfo WritableMap
   */
  @Override
  public void reject(
      @Nullable String code,
      @Nullable String message,
      @Nullable Throwable throwable,
      @Nullable HashMap userInfo) {
    if (mReject == null) {
      mResolve = null;
      return;
    }
    Map errorInfo = new HashMap();

    if (code == null) {
      errorInfo.put(ERROR_MAP_KEY_CODE, ERROR_DEFAULT_CODE);
    } else {
      errorInfo.put(ERROR_MAP_KEY_CODE, code);
    }

    // Use the custom message if provided otherwise use the throwable message.
    if (message != null) {
      errorInfo.put(ERROR_MAP_KEY_MESSAGE, message);
    } else if (throwable != null) {
      errorInfo.put(ERROR_MAP_KEY_MESSAGE, throwable.getMessage());
    } else {
      // The JavaScript side expects a map with at least an error message.
      // /Libraries/BatchedBridge/NativeModules.js -> createErrorFromErrorData
      // TYPE: (errorData: { message: string })
      errorInfo.put(ERROR_MAP_KEY_MESSAGE, ERROR_DEFAULT_MESSAGE);
    }

    // For consistency with iOS ensure userInfo key exists, even if we null it.
    // iOS: /React/Base/RCTUtils.m -> RCTJSErrorFromCodeMessageAndNSError
    if (userInfo != null) {
      errorInfo.put(ERROR_MAP_KEY_USER_INFO, userInfo);
    } else {
      errorInfo.put(ERROR_MAP_KEY_USER_INFO,null);
    }

    // Attach a nativeStackAndroid array if a throwable was passed
    // this matches iOS behavior - iOS adds a `nativeStackIOS` property
    // iOS: /React/Base/RCTUtils.m -> RCTJSErrorFromCodeMessageAndNSError
    if (throwable != null) {
      StackTraceElement[] stackTrace = throwable.getStackTrace();
      ArrayList nativeStackAndroid = new ArrayList<HashMap>();

      // Build an an Array of StackFrames to match JavaScript:
      // iOS: /Libraries/Core/Devtools/parseErrorStack.js -> StackFrame
      for (int i = 0; i < stackTrace.length && i < ERROR_STACK_FRAME_LIMIT; i++) {
        StackTraceElement frame = stackTrace[i];
        HashMap frameMap = new HashMap();
        // NOTE: no column number exists StackTraceElement
        frameMap.put(STACK_FRAME_KEY_CLASS, frame.getClassName());
        frameMap.put(STACK_FRAME_KEY_FILE, frame.getFileName());
        frameMap.put(STACK_FRAME_KEY_LINE_NUMBER, frame.getLineNumber());
        frameMap.put(STACK_FRAME_KEY_METHOD_NAME, frame.getMethodName());
        nativeStackAndroid.add(frameMap);
      }

      errorInfo.put(ERROR_MAP_KEY_NATIVE_STACK, nativeStackAndroid);
    } else {
      errorInfo.put(ERROR_MAP_KEY_NATIVE_STACK, new ArrayList());
    }

    mReject.invoke(errorInfo);
    mResolve = null;
    mReject = null;
  }

  /* ------------
   *  Deprecated
   * ------------ */

  @Override
  @Deprecated
  public void reject(String message) {
    reject(/*Code*/ null, message, /*Throwable*/ null, /*WritableMap*/ null);
  }
}