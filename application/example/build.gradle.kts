import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "com.example"
version = "0.0.1-SNAPSHOT"

plugins {
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.spring") version "2.0.20"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks {
    withType<BootJar> {
        enabled = true
    }
}
