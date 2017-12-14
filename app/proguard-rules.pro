# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
-keepattributes *Annotation*,Signature

-dontnote com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
-keepclassmembers class * extends com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper {
    <init>(android.content.Context);
}

-dontnote com.j256.ormlite.field.DatabaseFieldConfig
-keepclassmembers class com.j256.ormlite.field.DatabaseFieldConfig {
    <fields>;
}

-dontnote com.j256.ormlite.dao.Dao
-keepclassmembers class * implements com.j256.ormlite.dao.Dao {
    <init>(**);
    <init>(**, java.lang.Class);
}

-dontnote com.j256.ormlite.android.AndroidLog
-keep class com.j256.ormlite.android.AndroidLog {
    <init>(java.lang.String);
}

-dontnote com.j256.ormlite.table.DatabaseTable
-keep @com.j256.ormlite.table.DatabaseTable class * {
    void set*(***);
    *** get*();
}

-dontnote com.j256.ormlite.field.DatabaseField
-keepclassmembers @interface com.j256.ormlite.field.DatabaseField {
    <methods>;
}

-dontnote com.j256.ormlite.field.ForeignCollectionField
-keepclassmembers @interface com.j256.ormlite.field.ForeignCollectionField {
    <methods>;
}

-keepclasseswithmembers class * {
    @com.j256.ormlite.field.DatabaseField <fields>;
}

-keepclasseswithmembers class * {
    @com.j256.ormlite.field.ForeignCollectionField <fields>;
}

-dontnote org.joda.time.DateTime
-keep,allowobfuscation class org.joda.time.DateTime
-keepclassmembers class org.joda.time.DateTime {
    <init>(long);
 }