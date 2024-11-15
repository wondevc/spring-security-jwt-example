rootProject.name = "spring-security-jwt-example"

include(
    "example-core",
    "example-application",
    "external-jwt"
)

project(":example-core").projectDir = file("core")
project(":example-application").projectDir = file("application/example")
project(":external-jwt").projectDir = file("external/jwt")
