plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("net.kyori.indra") version "2.1.1"
    id("net.minecrell.plugin-yml.bungee") version "0.5.1"
    java
}

group = "fi.fabianadrian"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

indra {
    javaVersions().target(11)
}

dependencies {
    compileOnly("io.github.waterfallmc:waterfall-api:1.18-R0.1-SNAPSHOT")
    implementation("org.spongepowered:configurate-hocon:4.1.2")
    implementation("net.kyori:adventure-platform-bungeecord:4.1.0")
}

tasks {
    shadowJar {
        minimize()
        sequenceOf(
            "net.kyori.adventure",
            "org.spongepowered.configurate"
        ).forEach { pkg ->
            relocate(pkg, "${group}.${rootProject.name.toLowerCase()}.lib.$pkg")
        }
    }
    build {
        dependsOn(shadowJar)
    }
}

bungee {
    main = "fi.fabianadrian.hubcommand.HubCommand"
    name = rootProject.name
    author = "FabianAdrian"
}