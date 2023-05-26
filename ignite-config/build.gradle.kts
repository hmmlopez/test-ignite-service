plugins {
    prj.`kotlin-conventions`
}

dependencies {
    api(platform(libs.springBootDependencies))
    api(libs.bundles.ignite)
    api(project(":domain"))
    implementation("org.springframework.boot:spring-boot")
}

configurations {
    all {
        resolutionStrategy.eachDependency {
            if (requested.group == "com.h2database") {
                useVersion("1.4.197")
            }
        }
    }
}