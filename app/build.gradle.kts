plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.unont.lamutua"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.unont.lamutua"
        minSdk = 26
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("androidx.navigation:navigation-compose:2.8.9")
    implementation("androidx.compose.ui:ui:1.7.8") // Reemplaza con la última versión
    implementation("androidx.compose.material:material:1.7.8") // Reemplaza con la última versión
    implementation("androidx.compose.ui:ui-tooling-preview:1.7.8") // Reemplaza con la última versión
    debugImplementation("androidx.compose.ui:ui-tooling:1.7.8") // Reemplaza con la última versión
    implementation("androidx.activity:activity-compose:1.10.1") // Reemplaza con la última versión
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7") // Reemplaza con la última versión

    // Agrega las dependencias de los iconos que estas usando.
    implementation("androidx.compose.material:material-icons-extended:1.7.8") // Reemplaza con la última versión

    // Agrega las dependencias de los iconos.
    implementation("androidx.compose.material:material-icons-core:1.7.8")
    implementation("androidx.compose.material:material-icons-extended:1.5.4") // Reemplaza con la versión más reciente
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.33.2-alpha") // o la version mas reciente
    implementation("androidx.compose.material:material-icons-extended:1.6.4") // O la versión más reciente
}