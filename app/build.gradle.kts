import com.example.photoshow.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.example.photoshow"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.photoshow"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":usecases"))

    implementation(Libs.AndroidX.coreKtx)
    implementation(Libs.AndroidX.appCompat)
    implementation(Libs.AndroidX.material)
    implementation(Libs.AndroidX.constraintLayout)
    implementation(Libs.AndroidX.recyclerView)

    implementation(Libs.Lottie.lottie)

    implementation(Libs.AndroidX.Navigation.fragmentKtx)
    implementation(Libs.AndroidX.Navigation.uiKtx)
    implementation(Libs.AndroidX.Navigation.navCompose)

    implementation(Libs.Glide.glide)
    ksp(Libs.Glide.compiler)

    implementation(Libs.OkHttp3.loginInterceptor)
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converterGson)

    implementation(Libs.Hilt.android)
    kapt(Libs.Hilt.compiler)

    implementation(Libs.Arrow.core)

    implementation(Libs.AndroidX.Lifecycle.viewmodelKtx)
    implementation(Libs.AndroidX.Lifecycle.runtimeKtx)

    implementation(Libs.AndroidX.Room.runtime)
    implementation(Libs.AndroidX.Room.ktx)
    ksp(Libs.AndroidX.Room.compiler)

    testImplementation (project(":testShared"))
    testImplementation (Libs.JUnit.junit)
    testImplementation (Libs.Mockito.kotlin)
    testImplementation (Libs.Mockito.inline)
    testImplementation (Libs.Kotlin.Coroutines.test)
    testImplementation (Libs.turbine)
}
