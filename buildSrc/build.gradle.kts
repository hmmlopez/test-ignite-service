plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.plugin)
    implementation(libs.kotlin.spring.plugin)
    implementation(libs.spotless.plugin)
}