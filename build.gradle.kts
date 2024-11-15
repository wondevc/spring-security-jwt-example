import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.utils.addToStdlib.runIf

plugins {
    kotlin("jvm") version "2.0.20"
}

allprojects {
    repositories {
        mavenCentral()
    }

    tasks {
        withType<Jar> {
            enabled = false
        }

        withType<War> {
            enabled = false
        }
    }
}

subprojects {
    runIf(project.buildFile.exists()) {
        apply {
            plugin("org.jetbrains.kotlin.jvm")
        }

        java.sourceCompatibility = JavaVersion.VERSION_21

        dependencies {
            implementation(kotlin("stdlib"))
            implementation(kotlin("reflect"))
        }

        tasks {
            withType<KotlinCompile> {
                compilerOptions {
                    freeCompilerArgs.add("-Xjsr305=strict")
                    jvmTarget.set(JvmTarget.JVM_21)
                }
            }
        }
    }
}
