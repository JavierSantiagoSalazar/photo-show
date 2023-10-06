package com.example.photoshow

object Libs {

    const val androidGradlePlugin = "com.android.tools.build:gradle:8.1.1"
    const val gradleKspPlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.9.0-1.0.13"

    object Kotlin {
        private const val version = "1.9.0"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val jvmPlugin = "org.jetbrains.kotlin.jvm:kot:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.8.21"

        object Coroutines {
            private const val version = "1.6.4"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.10.0"
        const val appCompat = "androidx.appcompat:appcompat:1.6.1"
        const val material = "com.google.android.material:material:1.9.0"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.1.4"

        object Lifecycle {
            private const val version = "2.6.2"
            const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Navigation {
            private const val version = "2.6.0"
            const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:$version"
            const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
            const val navCompose = "androidx.navigation:navigation-compose:$version"
            const val gradlePlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
        }

        object Room {
            private const val version = "2.5.1"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Test {
            private const val rulesVersion = "1.5.0"
            private const val runnerVersion = "1.5.2"
            const val runner = "androidx.test:runner:$runnerVersion"
            const val rules = "androidx.test:rules:$rulesVersion"

            object Ext {
                private const val version = "1.1.5"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            object Espresso{
                private const val version="3.5.1"
                const val contrib = "androidx.test.espresso:espresso-contrib:$version"
            }
        }
    }

    object Lottie {
        private const val version = "6.0.0"
        const val lottie = "com.airbnb.android:lottie:$version"
    }

    object Glide {
        private const val version = "4.16.0"
        const val glide = "com.github.bumptech.glide:glide:$version"
        const val compiler = "com.github.bumptech.glide:compiler:$version"
    }


    object OkHttp3 {
        private const val version = "4.11.0"
        const val loginInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converterGson = "com.squareup.retrofit2:converter-gson:$version"
    }

    object Hilt {
        private const val version = "2.48"
        const val android = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val test = "com.google.dagger:hilt-android-testing:$version"
    }

    object Arrow {
        private const val version = "1.1.5"
        const val core = "io.arrow-kt:arrow-core:$version"
    }

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Mockito {
        const val kotlin = "org.mockito.kotlin:mockito-kotlin:4.1.0"
        const val inline = "org.mockito:mockito-inline:5.2.0"
    }

    object JavaX {
        const val inject = "javax.inject:javax.inject:1"
    }

    const val turbine = "app.cash.turbine:turbine:0.12.3"
}
