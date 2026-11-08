plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.lares"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.example.lares"
        minSdk = 34
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.foundation)
    implementation(libs.foundation.layout)
    implementation(libs.material)
    implementation(libs.runtime)
    implementation(libs.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
}