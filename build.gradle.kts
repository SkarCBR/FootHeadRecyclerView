// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
        maven { url = uri("https://raw.github.com/pinchbv/android-analyzer/master/repo") }
        maven { url = uri("http://developer.huawei.com/repo/") }
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.61")
        classpath("com.justpinch:androidanalyzer:1.1.1")
        classpath("com.huawei.agconnect:agcp:1.3.1.300")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("http://developer.huawei.com/repo/") }
    }
}

tasks.create<Delete>("clean") {
    delete(rootProject.buildDir)
}
