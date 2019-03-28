import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    dependencies {
        classpath("io.quarkus:quarkus-gradle-plugin:0.11.0")
    }
}

plugins {
    kotlin("jvm") version "1.3.21"
    id("io.quarkus.gradle.plugin") version "0.12.0"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.3.21"
}

group = "com.github.aesteve"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compileOnly("io.quarkus:quarkus-resteasy:0.12.0")
    implementation("io.quarkus:quarkus-kotlin:0.12.0")
    compileOnly("com.oracle.substratevm:svm:1.0.0-rc12") {
        exclude("*", "*")
    }
    compileOnly("io.quarkus:quarkus-arc:0.12.0") {
        exclude("com.oracle.substratevm", "svm")
    }
    implementation("org.jetbrains.intellij.deps:trove4j:1.0.20181211")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.3.21")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.3"
}

allOpen {
    annotation("javax.ws.rs.Path")
}

quarkus {
    setSourceDir("src/main/kotlin")
    setOutputDirectory("build/classes/kotlin/main")
}
