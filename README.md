Spring Security + Reactive + JJWT Example Project
=================================================


Table of contents
=================

<!---ts--->
* [Features](#Features)
  * [APIs](#APIs)
* [Installation](#Installation)
    * [Build](#Build)
    * [Run Application](#Run-Application)
* [Tech Stack](#Tech-Stack)
* [Module Structure](#Module-Structure)
<!---te--->


Features
========
* The JWT authentication process was implemented by referencing the approach used in 
[spring-security-oauth2](https://github.com/spring-projects/spring-security/tree/6.3.4/oauth2).

* Fully functional on amazon-corretto 21.0.4 JDKs

APIs
----



Installation
============

Build
-----
```bash
$ ./gradlew build

$ ./gradlew :core build

$ ./gradlew :external-jwt build

$ ./gradlew :application-example build
```

Run Application
---------------
```bash
$ ./gradlew :application-example:bootRun
```


Tech Stack
==========
![Java](https://img.shields.io/badge/Java-v21.0.4-brightgreen?style=flat&logo=openjdk&logoColor=000000)
![Kotlin](https://img.shields.io/badge/Kotlin-v2.0.20-brightgreen?style=flat&logo=kotlin&logoColor=7F52FF)
![Coroutines](https://img.shields.io/badge/Coroutine-grey?style=flat&logo=kotlin&logoColor=7F52FF)
![Gradle](https://img.shields.io/badge/Gradle-v8.10-brightgreen?style=flat&logo=gradle&logoColor=02303A)
![SpringBoot](https://img.shields.io/badge/Spring_Boot-v3.3.5-brightgreen?style=flat&logo=spring&logoColor=6DB33F)
![JJWT](https://img.shields.io/badge/JJWT-v0.12.6-brightgreen?style=flat&logo=jsonwebtokens&logoColor=000000)


Module Structure
================
```text
storelink6
├── application
│   └── example             # application-example module
├── core                    # core module
└── external
    └── jwt                 # external-jwt module
```
