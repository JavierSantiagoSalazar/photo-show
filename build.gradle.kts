plugins {
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
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
