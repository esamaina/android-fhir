plugins {
    id("com.android.application")
    id("kotlin-android")

}
val kotlin_version = "1.9.24"
android {
    namespace = "com.google.codelab.sdclibrary"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.google.android.fhir.codelabs.datacapture"
        minSdk = 26
        targetSdk = 34
        versionCode = 1

        // Avoid using the `ndkVersion` directly; use customNdkVersion
        val safeNdkVersion = ndkVersion
        versionName = "1.0.${safeNdkVersion.replace(Regex("[^a-zA-Z0-9._-]"), "")}"
        applicationIdSuffix = "debug"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    kotlinOptions {
        jvmTarget = "17" // Or use "1.8" if needed
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = false
    }

    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    packaging {
        resources.excludes.addAll(
            listOf("META-INF/ASL-2.0.txt", "META-INF/LGPL-3.0.txt")
        )
    }

    dependenciesInfo {
        includeInApk = false
        includeInBundle = true
    }

    // Remove `ndkVersion` from here as it might cause conflicts with custom property
    buildToolsVersion = (rootProject.extra["buildToolsVersion"] as? String) ?: "35.0.0"
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")
    implementation("androidx.core:core-ktx:1.15.0")
    androidTestImplementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.2.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
}
