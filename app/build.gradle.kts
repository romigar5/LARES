plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")

}

android {
    namespace = "com.example.lares"
    compileSdk = 37


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
    // 💡 ACTIVACIÓN OBLIGATORIA DE JETPACK COMPOSE
    buildFeatures {
        compose = true
    }
}

dependencies {
    // Librerías principales de Compose
    implementation(libs.appcompat)
    implementation(libs.foundation)
    implementation(libs.foundation.layout)
    implementation(libs.material3)
    implementation(libs.runtime)
    implementation(libs.storage)
    implementation(libs.ui)


    // Previews y herramientas visuales
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    debugImplementation(libs.ui.tooling)

    // Pruebas unitarias e instrumentadas
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)

}