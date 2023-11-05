plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "de.hs-anhalt"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation ("io.cucumber:cucumber-java:7.11.1")
//    implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
//    implementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
//    implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testImplementation(kotlin("test"))


}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(11)
}

application {
    mainClass.set("MainKt")
}