plugins {
    id("com.android.application")
    id("kotlin-android")

}
val kotlin_version = "2.0.21" //"1.9.24"
android {
    namespace = "com.google.codelab.sdclibrary"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.google.android.fhir.codelabs.datacapture"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        // Avoid using the `ndkVersion` directly; use customNdkVersion
        //val safeNdkVersion = ndkVersion
       // versionName = "1.0.${safeNdkVersion?.replace(Regex("[^a-zA-Z0-9._-]"), "") ?: "default"}"

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
        viewBinding = true
    }

//    compileOptions {
//        isCoreLibraryDesugaringEnabled = true
//        sourceCompatibility = JavaVersion.VERSION_17
//        targetCompatibility = JavaVersion.VERSION_17
//       //sourceCompatibility = JavaVersion.VERSION_1_8
//        //targetCompatibility = JavaVersion.VERSION_1_8
//    }
    compileOptions {
        // Flag to enable support for the new language APIs
        // See https://developer.android.com/studio/write/java8-support
        isCoreLibraryDesugaringEnabled = true
    }

    packaging { resources.excludes.addAll(listOf("META-INF/ASL-2.0.txt", "META-INF/LGPL-3.0.txt")) }
    kotlin { jvmToolchain(17) }



    // Remove `ndkVersion` from here as it might cause conflicts with custom property
    buildToolsVersion = (rootProject.extra["buildToolsVersion"] as? String) ?: "35.0.0"
}

dependencies {
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.2")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

//    implementation("ca.uhn.fhir:android-fhir:0.8.0")
    implementation("com.google.android.fhir:engine:1.1.0")
//    implementation("com.google.codelab.sdclibrary:sdk:1.0.0")
    // 3 Add dependencies for Structured Data Capture Library and Fragment KTX
   // implementation("com.google.android.fhir:fhir-engine:1.0.0")  // Add the FHIR Engine (core FHIR SDK)
    implementation("com.google.android.fhir:data-capture:1.2.0")  // FHIR Data Capture
    implementation("androidx.fragment:fragment-ktx:1.6.0")  // Fragment KTX for easy Fragment handling
//    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.1.4")
//    implementation("androidx.core:core-ktx:1.15.0")
//    implementation("androidx.appcompat:appcompat:1.6.0")
//    implementation("com.google.android.material:material:1.12.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.2.0")
//
//    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
//    implementation("com.google.android.fhir:data-capture:1.2.0")  // FHIR Data Capture
//    // implementation("androidx.fragment:fragment-ktx:1.8.5")
//    // implementation ("com.google.android.fhir:data-capture:1.0.0-beta06")
//    implementation("ca.uhn.hapi.fhir:hapi-fhir-base:6.8.0")
//    //implementation("ca.uhn.hapi.fhir:hapi-fhir-base:6.8.0")
//    implementation("ca.uhn.hapi.fhir:hapi-fhir-structures-r4:6.8.0")
//    implementation("com.google.android.fhir:fhir-engine:1.0.0")  // Add the FHIR Engine (core FHIR SDK)
//    implementation("androidx.fragment:fragment-ktx:1.6.0")  // Fragment KTX for easy Fragment handling
//
//    implementation("junit:junit:4.13.2")
//    implementation("androidx.test.ext:junit:1.2.1")
//    implementation("androidx.test.espresso:espresso-core:3.6.1")


}

repositories {
    google()
    mavenCentral()
}