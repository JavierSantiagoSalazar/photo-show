plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.android.library") version "8.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(com.example.photoshow.Libs.androidGradlePlugin)
        classpath(com.example.photoshow.Libs.Kotlin.gradlePlugin)
        classpath(com.example.photoshow.Libs.AndroidX.Navigation.gradlePlugin)
        classpath(com.example.photoshow.Libs.gradleKspPlugin)
        classpath(com.example.photoshow.Libs.Hilt.gradlePlugin)
    }
}
