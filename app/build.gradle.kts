plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "ru.plumsoftware.marvel"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.plumsoftware.marvel"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val navVersion = "2.7.6"
    val systemUIController = "0.32.0"

    val retrofit = "2.9.0"

    val moshi = "1.14.0"
    val moshiKotlin = "1.14.0"
    val converterMoshi = "2.5.0"

    val coilVersion = "2.5.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

//    Navigation
    implementation("androidx.navigation:navigation-common:$navVersion")
    implementation("androidx.navigation:navigation-compose:$navVersion")

//    System ui controller
    implementation("com.google.accompanist:accompanist-systemuicontroller:$systemUIController")

//    Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit")

//    Moshi
    implementation("com.squareup.moshi:moshi:$moshi")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiKotlin")
    implementation("com.squareup.retrofit2:converter-moshi:$converterMoshi")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:$moshi")

    //Coil
    implementation("io.coil-kt:coil-compose:$coilVersion")
}