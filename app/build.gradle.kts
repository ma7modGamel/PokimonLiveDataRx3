plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    id("kotlin-kapt")
    alias(libs.plugins.dagger.hilt)

}

android {
    namespace = "com.safwa.application"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.safwa.application"
        minSdk = 24
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
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)



    implementation (libs.androidx.recyclerview)
    implementation (libs.androidx.cardview)
    implementation(libs.multidex)

    // Hilt
    // implementation (libs.hilt.android)
//   annotationProcessor(libs.hilt.android.compiler)
//    implementation(libs.androidx.hilt.lifecycle.viewmodel)
//   annotationProcessor(libs.androidx.hilt.compiler)

    //RxJava
    implementation(libs.rxandroid)
    implementation(libs.rxjava)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation (libs.rxjava3.retrofit.adapter)
    implementation(libs.reactivenetwork)
    implementation(libs.converter.scalars)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp.urlconnection)


    debugImplementation(libs.chucker)
    releaseImplementation(libs.chucker.no.op)

    // ViewModel
    implementation(libs.androidx.lifecycle.viewmodel)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata)

    implementation(libs.timber)
    // Room
    implementation (libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    implementation (libs.androidx.room.rxjava2)

    // Navigation
    implementation (libs.androidx.navigation.fragment)
    implementation (libs.androidx.navigation.ui)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    //sdp
    implementation(libs.sdp.android)



    // ViewModel & Livedata
    implementation (libs.androidx.lifecycle.extensions)
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    kapt(libs.androidx.lifecycle.compiler)
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")


    // Lifecycles only (without ViewModel or LiveData)
    implementation (libs.androidx.lifecycle.runtime.ktx)

    // Saved state module for ViewModel
    implementation (libs.androidx.lifecycle.viewmodel.savedstate)

    implementation(libs.hilt)
    kapt(libs.hilt.android.compiler)
    kapt(libs.hilt.android.androidx.compiler)



}
kapt {
    correctErrorTypes = true
}