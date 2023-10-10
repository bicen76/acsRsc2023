plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.shaw.rsc2023"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.shaw.rsc2023"
        minSdk = 24
        targetSdk = 33
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.0")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation("io.reactivex.rxjava2:rxjava:2.2.6")

    implementation("androidx.room:room-runtime:2.5.2")
    annotationProcessor("androidx.room:room-compiler:2.5.2")
    //implementation ("com.google.dagger:dagger:2.x")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.5.2")
    // To use Kotlin Symbol Processing (KSP)
    //ksp("androidx.room:room-compiler:2.5.2")
    implementation("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    implementation("androidx.room:room-ktx:2.5.2")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.10.0")
    //implementation ("com.squareup.okhttp:okhttp:2.7.5")
    //implementation "androidx.room:room-rxjava2:$room_version"
    //implementation "androidx.room:room-rxjava3:$room_version"

}