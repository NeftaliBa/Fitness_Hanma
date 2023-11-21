plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.fitnes_hanma"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.fitnes_hanma"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


    implementation ("com.google.zxing:core:3.4.1")

    implementation ("androidx.multidex:multidex:2.0.1")

    implementation ("androidx.appcompat:appcompat:1.6.1")

    //implementation ("com.google.gms:google-services:4.4.0")

    implementation ("com.google.firebase:firebase-auth:22.3.0")

    implementation ("com.google.firebase:firebase-storage:20.3.0")

    implementation ("com.google.android.material:material:1.10.0")

    implementation ("com.google.firebase:firebase-database:20.3.0")

    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")

    implementation ("com.google.firebase:firebase-firestore:24.9.1")

    implementation ("com.google.firebase:firebase-analytics:21.5.0")

    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation ("com.google.android.gms:play-services-auth:20.7.0")

    implementation (platform("com.google.firebase:firebase-bom:32.3.1"))

    implementation ("com.github.bumptech.glide:glide:4.12.0")

    implementation ("com.squareup.picasso:picasso:2.71828")




}
