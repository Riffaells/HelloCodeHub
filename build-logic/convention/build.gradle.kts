
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}
group = "com.riffaells.hellocodehub.buildlogic"
java {
    sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
    targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get())
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.fromTarget(libs.versions.jvm.get())
    }
}

dependencies {

    implementation(libs.plugins.multiplatform)
    implementation(libs.plugins.compose)
    implementation(libs.plugins.kotlinx.serialization)
    implementation(libs.plugins.sqlDelight)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}
