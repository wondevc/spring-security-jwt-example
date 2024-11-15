rootProject.name = "spring-security-jwt-example"

include(
    "example-application",
)

project(":example-application").projectDir = file("application/example")
