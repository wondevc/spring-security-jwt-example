dependencies {
    implementation("com.github.f4b6a3:uuid-creator:6.0.0")
}

tasks {
    withType<Jar> {
        enabled = true
    }
}
