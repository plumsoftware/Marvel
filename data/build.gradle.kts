plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "ru.plumsoftware.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

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
}

dependencies {

    val retrofit = "2.9.0"

    val moshi = "1.14.0"
    val moshiKotlin = "1.14.0"
    val converterMoshi = "2.5.0"

    val roomVersion = "2.6.1"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofit")

//    Moshi
    implementation("com.squareup.moshi:moshi:$moshi")
    implementation("com.squareup.moshi:moshi-kotlin:$moshiKotlin")
    implementation("com.squareup.retrofit2:converter-moshi:$converterMoshi")

//    Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

//    KSP
    ksp("androidx.room:room-compiler:$roomVersion")

//    Module
    implementation(project(path=":domain"))
}