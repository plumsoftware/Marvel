plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.gms.google-services")
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

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
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
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    val roomVersion = "2.6.1"

    val navVersion = "2.7.6"
    val systemUIController = "0.32.0"

    val retrofit = "2.9.0"

    val moshi = "1.14.0"
    val moshiKotlin = "1.14.0"
    val converterMoshi = "2.5.0"

    val coilVersion = "2.5.0"

    val koinVersion = "3.4.2"

    val firebaseVersion = "23.4.0"

//    Auto-generate
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

//    Coil
    implementation("io.coil-kt:coil-compose:$coilVersion")

//    Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

//    KSP
    ksp("androidx.room:room-compiler:$roomVersion")

//    Koin
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")

//    Firebase
    implementation("com.google.firebase:firebase-messaging:$firebaseVersion")

//    Modules
    implementation(project(path=":domain"))
    implementation(project(path=":data"))
}