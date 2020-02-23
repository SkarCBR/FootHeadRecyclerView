// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven {url = uri("https://plugins.gradle.org/m2/") }
        maven {url = uri("https://raw.github.com/pinchbv/android-analyzer/master/repo") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath("com.justpinch:androidanalyzer:1.1.1")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
