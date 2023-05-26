plugins {
    prj.`kotlin-conventions`
}

dependencies {
    implementation(project(":ignite-config"))
    implementation("org.springframework.boot:spring-boot-starter")
}