plugins {
    id("java")
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

allprojects{
    group = "org.example"
    version = "1.0-SNAPSHOT"
    apply(plugin = "java")

    dependencies {
        implementation("com.intellij:annotations:12.0")
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}