plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

val BASE_URL = "BASE_URL"
android {
    namespace = "com.bismark.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        buildConfigField("String",BASE_URL,"\"http://localhost:8080/api/\"")
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    room{
        schemaDirectory("$projectDir/schemas")
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

    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    api(libs.android.retrofit)
    api(libs.android.retrofit.gson)
    api(libs.okhttp.network)
    implementation(libs.okhttp.loging.network)
    api(libs.android.room)
    implementation(libs.android.room.ktx)
    ksp(libs.android.room.compiler)
    implementation(libs.javax.android)

    debugImplementation(libs.chucker.op)
    releaseImplementation(libs.chucker.no.op)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}