rootProject.name = "spring-security-jwt-example"

include(
    "example-core",
    "example-application",
)

project(":example-core").projectDir = file("core")
project(":example-application").projectDir = file("application/example")