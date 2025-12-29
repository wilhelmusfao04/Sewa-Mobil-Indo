# proguard-rules.pro
-keepattributes Signature,InnerClasses,EnclosingMethod
-keepattributes RuntimeVisibleAnnotations,RuntimeVisibleParameterAnnotations,AnnotationDefault
-keepattributes *Annotation*

-dontwarn javax.annotation.**
-dontwarn kotlin.Metadata
-dontwarn okio.**
-dontwarn org.conscrypt.**
-dontwarn retrofit2.**
-dontwarn okhttp3.**

# Retrofit / OkHttp
-keep class retrofit2.** { *; }
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

# Gson
-keep class com.google.gson.** { *; }
-keep class * extends com.google.gson.TypeAdapter { *; }
-keep class * implements com.google.gson.TypeAdapterFactory { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# Room
-keep class androidx.room.** { *; }
-keep class com.example.sewamobil.db.** { *; }
-keep class com.example.sewamobil.db.entity.** { *; }
-keep class com.example.sewamobil.db.dao.** { *; }

# Model
-keep class com.example.sewamobil.model.** { *; }

# AndroidManifest.xml (snippet)
<application
    android:label="${appLabel}"
    android:icon="@mipmap/ic_launcher"
    android:roundIcon="@mipmap/ic_launcher_round"
    android:supportsRtl="true"
    android:usesCleartextTraffic="false"
    android:networkSecurityConfig="@xml/network_security_config"
    android:theme="@style/Theme.SewaMobil.Sky" />

# res/xml/network_security_config.xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="false">
        <trust-anchors>
            <certificates src="system" />
        </trust-anchors>
    </base-config>
</network-security-config>
