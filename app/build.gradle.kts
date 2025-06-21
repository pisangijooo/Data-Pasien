plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.projekakhir_uas_Intantriyulianti_Muhammadarioardhi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    // OpenStreetMap
    implementation("org.osmdroid:osmdroid-android:6.1.14")

    // AndroidX dan Material
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    implementation(libs.activity)

    // Unit Test & Instrumented Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
