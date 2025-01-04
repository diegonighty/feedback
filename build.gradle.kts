plugins {
    java
    id("com.github.johnrengelman.shadow") version("8.1.1")
    id("maven-publish")
}

repositories {
    mavenCentral()
    maven("https://repo.spongepowered.org/maven/")
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("org.spongepowered:configurate-yaml:4.2.0-SNAPSHOT")
    compileOnly("net.kyori:adventure-text-minimessage:4.17.0")
    compileOnly("net.kyori:adventure-api:4.17.0")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}