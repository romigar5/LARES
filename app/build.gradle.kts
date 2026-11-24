plugins {
    alias(libs.plugins.android.application)
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")

}

android {
    namespace = "com.example.lares"
    compileSdk = 35


    defaultConfig {
        applicationId = "com.example.lares"
        minSdk = 34
        targetSdk = 35
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
    kotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
        }
    }
    // 💡 ACTIVACIÓN OBLIGATORIA DE JETPACK COMPOSE
    buildFeatures {
        compose = true
    }
}

dependencies {
    // 1. Usar la plataforma BOM para sincronizar todas las versiones de Compose a una versión estable
    val composeBom = platform("androidx.compose:compose-bom:2024.10.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // 2. Componentes principales de Compose (sin especificar versión gracias al BOM)
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.foundation:foundation-layout")

    // Herramientas de desarrollo
    debugImplementation("androidx.compose.ui:ui-tooling")

    // 3. Compatibilidad Android básica e Iconos extendidos si los necesitas
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("androidx.compose.material:material-icons-extended")

    // Añadir Material Components para soportar los temas XML tradicionales
    implementation("com.google.android.material:material:1.12.0")

    // Pruebas
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
}