rootProject.name = "spring-security-jwt-example"

include(
    "core",
    "application-example",
    "external-jwt"
)

project(":application-example").projectDir = file("application/example")
project(":external-jwt").projectDir = file("external/jwt")
