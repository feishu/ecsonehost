package io.realm;


import android.annotation.TargetApi;
import android.os.Build;
import android.util.JsonReader;
import android.util.JsonToken;
import io.realm.ImportFlag;
import io.realm.ProxyUtils;
import io.realm.exceptions.RealmMigrationNeededException;
import io.realm.internal.ColumnInfo;
import io.realm.internal.OsList;
import io.realm.internal.OsObject;
import io.realm.internal.OsObjectSchemaInfo;
import io.realm.internal.OsSchemaInfo;
import io.realm.internal.Property;
import io.realm.internal.RealmObjectProxy;
import io.realm.internal.Row;
import io.realm.internal.Table;
import io.realm.internal.android.JsonUtils;
import io.realm.internal.objectstore.OsObjectBuilder;
import io.realm.log.RealmLog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("all")
public class com_weexbox_core_model_Md5RealmRealmProxy extends com.weexbox.core.model.Md5Realm
    implements RealmObjectProxy, com_weexbox_core_model_Md5RealmRealmProxyInterface {

    static final class Md5RealmColumnInfo extends ColumnInfo {
        long maxColumnIndexValue;
        long pathIndex;
        long md5Index;

        Md5RealmColumnInfo(OsSchemaInfo schemaInfo) {
            super(2);
            OsObjectSchemaInfo objectSchemaInfo = schemaInfo.getObjectSchemaInfo("Md5Realm");
            this.pathIndex = addColumnDetails("path", "path", objectSchemaInfo);
            this.md5Index = addColumnDetails("md5", "md5", objectSchemaInfo);
            this.maxColumnIndexValue = objectSchemaInfo.getMaxColumnIndex();
        }

        Md5RealmColumnInfo(ColumnInfo src, boolean mutable) {
            super(src, mutable);
            copy(src, this);
        }

        @Override
        protected final ColumnInfo copy(boolean mutable) {
            return new Md5RealmColumnInfo(this, mutable);
        }

        @Override
        protected final void copy(ColumnInfo rawSrc, ColumnInfo rawDst) {
            final Md5RealmColumnInfo src = (Md5RealmColumnInfo) rawSrc;
            final Md5RealmColumnInfo dst = (Md5RealmColumnInfo) rawDst;
            dst.pathIndex = src.pathIndex;
            dst.md5Index = src.md5Index;
            dst.maxColumnIndexValue = src.maxColumnIndexValue;
        }
    }

    private static final OsObjectSchemaInfo expectedObjectSchemaInfo = createExpectedObjectSchemaInfo();

    private Md5RealmColumnInfo columnInfo;
    private ProxyState<com.weexbox.core.model.Md5Realm> proxyState;

    com_weexbox_core_model_Md5RealmRealmProxy() {
        proxyState.setConstructionFinished();
    }

    @Override
    public void realm$injectObjectContext() {
        if (this.proxyState != null) {
            return;
        }
        final BaseRealm.RealmObjectContext context = BaseRealm.objectContext.get();
        this.columnInfo = (Md5RealmColumnInfo) context.getColumnInfo();
        this.proxyState = new ProxyState<com.weexbox.core.model.Md5Realm>(this);
        proxyState.setRealm$realm(context.getRealm());
        proxyState.setRow$realm(context.getRow());
        proxyState.setAcceptDefaultValue$realm(context.getAcceptDefaultValue());
        proxyState.setExcludeFields$realm(context.getExcludeFields());
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$path() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.pathIndex);
    }

    @Override
    public void realmSet$path(String value) {
        if (proxyState.isUnderConstruction()) {
            // default value of the primary key is always ignored.
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        throw new io.realm.exceptions.RealmException("Primary key field 'path' cannot be changed after object was created.");
    }

    @Override
    @SuppressWarnings("cast")
    public String realmGet$md5() {
        proxyState.getRealm$realm().checkIfValid();
        return (java.lang.String) proxyState.getRow$realm().getString(columnInfo.md5Index);
    }

    @Override
    public void realmSet$md5(String value) {
        if (proxyState.isUnderConstruction()) {
            if (!proxyState.getAcceptDefaultValue$realm()) {
                return;
            }
            final Row row = proxyState.getRow$realm();
            if (value == null) {
                row.getTable().setNull(columnInfo.md5Index, row.getIndex(), true);
                return;
            }
            row.getTable().setString(columnInfo.md5Index, row.getIndex(), value, true);
            return;
        }

        proxyState.getRealm$realm().checkIfValid();
        if (value == null) {
            proxyState.getRow$realm().setNull(columnInfo.md5Index);
            return;
        }
        proxyState.getRow$realm().setString(columnInfo.md5Index, value);
    }

    private static OsObjectSchemaInfo createExpectedObjectSchemaInfo() {
        OsObjectSchemaInfo.Builder builder = new OsObjectSchemaInfo.Builder("Md5Realm", 2, 0);
        builder.addPersistedProperty("path", RealmFieldType.STRING, Property.PRIMARY_KEY, Property.INDEXED, !Property.REQUIRED);
        builder.addPersistedProperty("md5", RealmFieldType.STRING, !Property.PRIMARY_KEY, !Property.INDEXED, !Property.REQUIRED);
        return builder.build();
    }

    public static OsObjectSchemaInfo getExpectedObjectSchemaInfo() {
        return expectedObjectSchemaInfo;
    }

    public static Md5RealmColumnInfo createColumnInfo(OsSchemaInfo schemaInfo) {
        return new Md5RealmColumnInfo(schemaInfo);
    }

    public static String getSimpleClassName() {
        return "Md5Realm";
    }

    public static final class ClassNameHelper {
        public static final String INTERNAL_CLASS_NAME = "Md5Realm";
    }

    @SuppressWarnings("cast")
    public static com.weexbox.core.model.Md5Realm createOrUpdateUsingJsonObject(Realm realm, JSONObject json, boolean update)
        throws JSONException {
        final List<String> excludeFields = Collections.<String> emptyList();
        com.weexbox.core.model.Md5Realm obj = null;
        if (update) {
            Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
            Md5RealmColumnInfo columnInfo = (Md5RealmColumnInfo) realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class);
            long pkColumnIndex = columnInfo.pathIndex;
            long rowIndex = Table.NO_MATCH;
            if (json.isNull("path")) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, json.getString("path"));
            }
            if (rowIndex != Table.NO_MATCH) {
                final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class), false, Collections.<String> emptyList());
                    obj = new io.realm.com_weexbox_core_model_Md5RealmRealmProxy();
                } finally {
                    objectContext.clear();
                }
            }
        }
        if (obj == null) {
            if (json.has("path")) {
                if (json.isNull("path")) {
                    obj = (io.realm.com_weexbox_core_model_Md5RealmRealmProxy) realm.createObjectInternal(com.weexbox.core.model.Md5Realm.class, null, true, excludeFields);
                } else {
                    obj = (io.realm.com_weexbox_core_model_Md5RealmRealmProxy) realm.createObjectInternal(com.weexbox.core.model.Md5Realm.class, json.getString("path"), true, excludeFields);
                }
            } else {
                throw new IllegalArgumentException("JSON object doesn't have the primary key field 'path'.");
            }
        }

        final com_weexbox_core_model_Md5RealmRealmProxyInterface objProxy = (com_weexbox_core_model_Md5RealmRealmProxyInterface) obj;
        if (json.has("md5")) {
            if (json.isNull("md5")) {
                objProxy.realmSet$md5(null);
            } else {
                objProxy.realmSet$md5((String) json.getString("md5"));
            }
        }
        return obj;
    }

    @SuppressWarnings("cast")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static com.weexbox.core.model.Md5Realm createUsingJsonStream(Realm realm, JsonReader reader)
        throws IOException {
        boolean jsonHasPrimaryKey = false;
        final com.weexbox.core.model.Md5Realm obj = new com.weexbox.core.model.Md5Realm();
        final com_weexbox_core_model_Md5RealmRealmProxyInterface objProxy = (com_weexbox_core_model_Md5RealmRealmProxyInterface) obj;
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (false) {
            } else if (name.equals("path")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$path((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$path(null);
                }
                jsonHasPrimaryKey = true;
            } else if (name.equals("md5")) {
                if (reader.peek() != JsonToken.NULL) {
                    objProxy.realmSet$md5((String) reader.nextString());
                } else {
                    reader.skipValue();
                    objProxy.realmSet$md5(null);
                }
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        if (!jsonHasPrimaryKey) {
            throw new IllegalArgumentException("JSON object doesn't have the primary key field 'path'.");
        }
        return realm.copyToRealm(obj);
    }

    private static com_weexbox_core_model_Md5RealmRealmProxy newProxyInstance(BaseRealm realm, Row row) {
        // Ignore default values to avoid creating uexpected objects from RealmModel/RealmList fields
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        objectContext.set(realm, row, realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class), false, Collections.<String>emptyList());
        io.realm.com_weexbox_core_model_Md5RealmRealmProxy obj = new io.realm.com_weexbox_core_model_Md5RealmRealmProxy();
        objectContext.clear();
        return obj;
    }

    public static com.weexbox.core.model.Md5Realm copyOrUpdate(Realm realm, Md5RealmColumnInfo columnInfo, com.weexbox.core.model.Md5Realm object, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null) {
            final BaseRealm otherRealm = ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm();
            if (otherRealm.threadId != realm.threadId) {
                throw new IllegalArgumentException("Objects which belong to Realm instances in other threads cannot be copied into this Realm instance.");
            }
            if (otherRealm.getPath().equals(realm.getPath())) {
                return object;
            }
        }
        final BaseRealm.RealmObjectContext objectContext = BaseRealm.objectContext.get();
        RealmObjectProxy cachedRealmObject = cache.get(object);
        if (cachedRealmObject != null) {
            return (com.weexbox.core.model.Md5Realm) cachedRealmObject;
        }

        com.weexbox.core.model.Md5Realm realmObject = null;
        boolean canUpdate = update;
        if (canUpdate) {
            Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
            long pkColumnIndex = columnInfo.pathIndex;
            String value = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$path();
            long rowIndex = Table.NO_MATCH;
            if (value == null) {
                rowIndex = table.findFirstNull(pkColumnIndex);
            } else {
                rowIndex = table.findFirstString(pkColumnIndex, value);
            }
            if (rowIndex == Table.NO_MATCH) {
                canUpdate = false;
            } else {
                try {
                    objectContext.set(realm, table.getUncheckedRow(rowIndex), columnInfo, false, Collections.<String> emptyList());
                    realmObject = new io.realm.com_weexbox_core_model_Md5RealmRealmProxy();
                    cache.put(object, (RealmObjectProxy) realmObject);
                } finally {
                    objectContext.clear();
                }
            }
        }

        return (canUpdate) ? update(realm, columnInfo, realmObject, object, cache, flags) : copy(realm, columnInfo, object, update, cache, flags);
    }

    public static com.weexbox.core.model.Md5Realm copy(Realm realm, Md5RealmColumnInfo columnInfo, com.weexbox.core.model.Md5Realm newObject, boolean update, Map<RealmModel,RealmObjectProxy> cache, Set<ImportFlag> flags) {
        RealmObjectProxy cachedRealmObject = cache.get(newObject);
        if (cachedRealmObject != null) {
            return (com.weexbox.core.model.Md5Realm) cachedRealmObject;
        }

        com_weexbox_core_model_Md5RealmRealmProxyInterface realmObjectSource = (com_weexbox_core_model_Md5RealmRealmProxyInterface) newObject;

        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);

        // Add all non-"object reference" fields
        builder.addString(columnInfo.pathIndex, realmObjectSource.realmGet$path());
        builder.addString(columnInfo.md5Index, realmObjectSource.realmGet$md5());

        // Create the underlying object and cache it before setting any object/objectlist references
        // This will allow us to break any circular dependencies by using the object cache.
        Row row = builder.createNewObject();
        io.realm.com_weexbox_core_model_Md5RealmRealmProxy realmObjectCopy = newProxyInstance(realm, row);
        cache.put(newObject, realmObjectCopy);

        return realmObjectCopy;
    }

    public static long insert(Realm realm, com.weexbox.core.model.Md5Realm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        long tableNativePtr = table.getNativePtr();
        Md5RealmColumnInfo columnInfo = (Md5RealmColumnInfo) realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class);
        long pkColumnIndex = columnInfo.pathIndex;
        String primaryKeyValue = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$path();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        } else {
            Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$md5 = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$md5();
        if (realmGet$md5 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.md5Index, rowIndex, realmGet$md5, false);
        }
        return rowIndex;
    }

    public static void insert(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        long tableNativePtr = table.getNativePtr();
        Md5RealmColumnInfo columnInfo = (Md5RealmColumnInfo) realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class);
        long pkColumnIndex = columnInfo.pathIndex;
        com.weexbox.core.model.Md5Realm object = null;
        while (objects.hasNext()) {
            object = (com.weexbox.core.model.Md5Realm) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$path();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            } else {
                Table.throwDuplicatePrimaryKeyException(primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$md5 = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$md5();
            if (realmGet$md5 != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.md5Index, rowIndex, realmGet$md5, false);
            }
        }
    }

    public static long insertOrUpdate(Realm realm, com.weexbox.core.model.Md5Realm object, Map<RealmModel,Long> cache) {
        if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
            return ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex();
        }
        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        long tableNativePtr = table.getNativePtr();
        Md5RealmColumnInfo columnInfo = (Md5RealmColumnInfo) realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class);
        long pkColumnIndex = columnInfo.pathIndex;
        String primaryKeyValue = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$path();
        long rowIndex = Table.NO_MATCH;
        if (primaryKeyValue == null) {
            rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
        } else {
            rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
        }
        if (rowIndex == Table.NO_MATCH) {
            rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
        }
        cache.put(object, rowIndex);
        String realmGet$md5 = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$md5();
        if (realmGet$md5 != null) {
            Table.nativeSetString(tableNativePtr, columnInfo.md5Index, rowIndex, realmGet$md5, false);
        } else {
            Table.nativeSetNull(tableNativePtr, columnInfo.md5Index, rowIndex, false);
        }
        return rowIndex;
    }

    public static void insertOrUpdate(Realm realm, Iterator<? extends RealmModel> objects, Map<RealmModel,Long> cache) {
        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        long tableNativePtr = table.getNativePtr();
        Md5RealmColumnInfo columnInfo = (Md5RealmColumnInfo) realm.getSchema().getColumnInfo(com.weexbox.core.model.Md5Realm.class);
        long pkColumnIndex = columnInfo.pathIndex;
        com.weexbox.core.model.Md5Realm object = null;
        while (objects.hasNext()) {
            object = (com.weexbox.core.model.Md5Realm) objects.next();
            if (cache.containsKey(object)) {
                continue;
            }
            if (object instanceof RealmObjectProxy && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm() != null && ((RealmObjectProxy) object).realmGet$proxyState().getRealm$realm().getPath().equals(realm.getPath())) {
                cache.put(object, ((RealmObjectProxy) object).realmGet$proxyState().getRow$realm().getIndex());
                continue;
            }
            String primaryKeyValue = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$path();
            long rowIndex = Table.NO_MATCH;
            if (primaryKeyValue == null) {
                rowIndex = Table.nativeFindFirstNull(tableNativePtr, pkColumnIndex);
            } else {
                rowIndex = Table.nativeFindFirstString(tableNativePtr, pkColumnIndex, primaryKeyValue);
            }
            if (rowIndex == Table.NO_MATCH) {
                rowIndex = OsObject.createRowWithPrimaryKey(table, pkColumnIndex, primaryKeyValue);
            }
            cache.put(object, rowIndex);
            String realmGet$md5 = ((com_weexbox_core_model_Md5RealmRealmProxyInterface) object).realmGet$md5();
            if (realmGet$md5 != null) {
                Table.nativeSetString(tableNativePtr, columnInfo.md5Index, rowIndex, realmGet$md5, false);
            } else {
                Table.nativeSetNull(tableNativePtr, columnInfo.md5Index, rowIndex, false);
            }
        }
    }

    public static com.weexbox.core.model.Md5Realm createDetachedCopy(com.weexbox.core.model.Md5Realm realmObject, int currentDepth, int maxDepth, Map<RealmModel, CacheData<RealmModel>> cache) {
        if (currentDepth > maxDepth || realmObject == null) {
            return null;
        }
        CacheData<RealmModel> cachedObject = cache.get(realmObject);
        com.weexbox.core.model.Md5Realm unmanagedObject;
        if (cachedObject == null) {
            unmanagedObject = new com.weexbox.core.model.Md5Realm();
            cache.put(realmObject, new RealmObjectProxy.CacheData<RealmModel>(currentDepth, unmanagedObject));
        } else {
            // Reuse cached object or recreate it because it was encountered at a lower depth.
            if (currentDepth >= cachedObject.minDepth) {
                return (com.weexbox.core.model.Md5Realm) cachedObject.object;
            }
            unmanagedObject = (com.weexbox.core.model.Md5Realm) cachedObject.object;
            cachedObject.minDepth = currentDepth;
        }
        com_weexbox_core_model_Md5RealmRealmProxyInterface unmanagedCopy = (com_weexbox_core_model_Md5RealmRealmProxyInterface) unmanagedObject;
        com_weexbox_core_model_Md5RealmRealmProxyInterface realmSource = (com_weexbox_core_model_Md5RealmRealmProxyInterface) realmObject;
        unmanagedCopy.realmSet$path(realmSource.realmGet$path());
        unmanagedCopy.realmSet$md5(realmSource.realmGet$md5());

        return unmanagedObject;
    }

    static com.weexbox.core.model.Md5Realm update(Realm realm, Md5RealmColumnInfo columnInfo, com.weexbox.core.model.Md5Realm realmObject, com.weexbox.core.model.Md5Realm newObject, Map<RealmModel, RealmObjectProxy> cache, Set<ImportFlag> flags) {
        com_weexbox_core_model_Md5RealmRealmProxyInterface realmObjectTarget = (com_weexbox_core_model_Md5RealmRealmProxyInterface) realmObject;
        com_weexbox_core_model_Md5RealmRealmProxyInterface realmObjectSource = (com_weexbox_core_model_Md5RealmRealmProxyInterface) newObject;
        Table table = realm.getTable(com.weexbox.core.model.Md5Realm.class);
        OsObjectBuilder builder = new OsObjectBuilder(table, columnInfo.maxColumnIndexValue, flags);
        builder.addString(columnInfo.pathIndex, realmObjectSource.realmGet$path());
        builder.addString(columnInfo.md5Index, realmObjectSource.realmGet$md5());

        builder.updateExistingObject();
        return realmObject;
    }

    @Override
    @SuppressWarnings("ArrayToString")
    public String toString() {
        if (!RealmObject.isValid(this)) {
            return "Invalid object";
        }
        StringBuilder stringBuilder = new StringBuilder("Md5Realm = proxy[");
        stringBuilder.append("{path:");
        stringBuilder.append(realmGet$path() != null ? realmGet$path() : "null");
        stringBuilder.append("}");
        stringBuilder.append(",");
        stringBuilder.append("{md5:");
        stringBuilder.append(realmGet$md5() != null ? realmGet$md5() : "null");
        stringBuilder.append("}");
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    @Override
    public ProxyState<?> realmGet$proxyState() {
        return proxyState;
    }

    @Override
    public int hashCode() {
        String realmName = proxyState.getRealm$realm().getPath();
        String tableName = proxyState.getRow$realm().getTable().getName();
        long rowIndex = proxyState.getRow$realm().getIndex();

        int result = 17;
        result = 31 * result + ((realmName != null) ? realmName.hashCode() : 0);
        result = 31 * result + ((tableName != null) ? tableName.hashCode() : 0);
        result = 31 * result + (int) (rowIndex ^ (rowIndex >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        com_weexbox_core_model_Md5RealmRealmProxy aMd5Realm = (com_weexbox_core_model_Md5RealmRealmProxy)o;

        String path = proxyState.getRealm$realm().getPath();
        String otherPath = aMd5Realm.proxyState.getRealm$realm().getPath();
        if (path != null ? !path.equals(otherPath) : otherPath != null) return false;

        String tableName = proxyState.getRow$realm().getTable().getName();
        String otherTableName = aMd5Realm.proxyState.getRow$realm().getTable().getName();
        if (tableName != null ? !tableName.equals(otherTableName) : otherTableName != null) return false;

        if (proxyState.getRow$realm().getIndex() != aMd5Realm.proxyState.getRow$realm().getIndex()) return false;

        return true;
    }
}
