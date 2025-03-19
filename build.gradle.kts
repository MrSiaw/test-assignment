plugins {
    id("java")
    id("io.qameta.allure") version "2.11.2"
}

group = "com.assignment"
version = "1.0-SNAPSHOT"

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.testng:testng:7.7.1")
    implementation("com.codeborne:selenide:6.19.1")
    implementation("org.slf4j:slf4j-simple:2.0.9")
    implementation("io.qameta.allure:allure-testng:2.11.2")
    implementation("io.rest-assured:rest-assured:5.4.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.17.3")
    implementation("com.fasterxml.jackson.module:jackson-module-jsonSchema:2.15.0")
    implementation("com.github.java-json-tools:json-schema-validator:2.2.14")
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng.xml")
        listeners.add("io.qameta.allure.testng.AllureTestNg")
    }
}