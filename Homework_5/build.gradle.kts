plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:20.1.0")
    implementation("org.projectlombok:lombok:1.18.24")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")

    implementation("org.flywaydb:flyway-core:9.6.0")
    implementation("org.postgresql:postgresql:42.5.0")

    implementation("com.google.inject:guice:5.0.1")

    implementation("com.google.code.gson:gson:2.7")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}