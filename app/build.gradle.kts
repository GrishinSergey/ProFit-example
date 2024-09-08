import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.application.gradle.plugin)
    alias(libs.plugins.kotlin.android.gradle.plugin)
    alias(libs.plugins.jetbrains.compose.compiler)
    alias(libs.plugins.ksp.devtools.gradle.plugin)
    alias(libs.plugins.kotlin.parcelize.gradle.plugin)
    alias(libs.plugins.dagger.hilt.gradle.plugin)
}

android {
    namespace = "com.sagrishin.profit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.sagrishin.profit"
        minSdk = 29
        targetSdk = 34

        versionCode = 1
        versionName = "1.0.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

composeCompiler {
    enableStrongSkippingMode = true
    includeSourceInformation = true
}

hilt {
    enableAggregatingTask = false
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(libs.compose.navigation)

    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.compose)
    debugImplementation(libs.bundles.compose.tooling)

    implementation(libs.nav.library)
    implementation(libs.nav.generator)
    ksp(libs.nav.generator)

    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.compiler)
}
