// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.application.gradle.plugin) apply false
    alias(libs.plugins.kotlin.android.gradle.plugin) apply false
    alias(libs.plugins.ksp.devtools.gradle.plugin) apply false
    alias(libs.plugins.kotlin.parcelize.gradle.plugin) apply false
    alias(libs.plugins.jetbrains.compose.compiler) apply false
    alias(libs.plugins.dagger.hilt.gradle.plugin) apply false
    alias(libs.plugins.android.library) apply false
}
