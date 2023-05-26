plugins {
    id("org.jetbrains.kotlin.jvm")
    id("com.diffplug.spotless")
    id("org.jetbrains.kotlin.plugin.spring")
}

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}