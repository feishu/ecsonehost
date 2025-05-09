
import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR\u0011\u0010\u000f\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\nR\u0011\u0010\u0011\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR\u0011\u0010\u0013\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\nR\u0011\u0010\u0015\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\n\u00a8\u0006\u001b"}, d2 = {"LVersionUtil;", "", "()V", "appVersionName", "", "getAppVersionName", "()Ljava/lang/String;", "hasHoneycomb", "", "getHasHoneycomb", "()Z", "hasJellyBean", "getHasJellyBean", "hasJellyBeanMR1", "getHasJellyBeanMR1", "hasJellyBeanMR2", "getHasJellyBeanMR2", "hasKitkat", "getHasKitkat", "hasLollipop", "getHasLollipop", "hasM", "getHasM", "compareVersion", "", "v1", "v2", "core_debug"})
public final class VersionUtil {
    private static final boolean hasJellyBeanMR2 = false;
    private static final boolean hasHoneycomb = false;
    private static final boolean hasJellyBean = false;
    private static final boolean hasJellyBeanMR1 = false;
    private static final boolean hasKitkat = false;
    private static final boolean hasLollipop = false;
    private static final boolean hasM = false;
    public static final VersionUtil INSTANCE = null;
    
    public final boolean getHasJellyBeanMR2() {
        return false;
    }
    
    public final boolean getHasHoneycomb() {
        return false;
    }
    
    public final boolean getHasJellyBean() {
        return false;
    }
    
    public final boolean getHasJellyBeanMR1() {
        return false;
    }
    
    public final boolean getHasKitkat() {
        return false;
    }
    
    public final boolean getHasLollipop() {
        return false;
    }
    
    public final boolean getHasM() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAppVersionName() {
        return null;
    }
    
    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * *
     * @param version2
     * *
     * @return
     */
    public final int compareVersion(@org.jetbrains.annotations.Nullable()
    java.lang.String v1, @org.jetbrains.annotations.Nullable()
    java.lang.String v2) {
        return 0;
    }
    
    private VersionUtil() {
        super();
    }
}