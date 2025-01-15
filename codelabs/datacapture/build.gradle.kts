// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val buildToolsVersion by extra("35.0.0")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}
val ndkVersion by extra("debug")



allprojects {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}