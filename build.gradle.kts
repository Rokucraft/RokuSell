plugins {
    `java-library`
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.2.3"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.rokucraft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.19.4-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")

    implementation("org.incendo:cloud-paper:2.0.0-beta.5")
    implementation("org.spongepowered:configurate-yaml:4.1.2")

    library("com.google.dagger:dagger:2.51")
    annotationProcessor("com.google.dagger:dagger-compiler:2.51")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(17)
}

bukkit {
    name = rootProject.name
    version = project.version.toString()
    main = "com.rokucraft.rokusell.RokuSell"
    apiVersion = "1.19"
    author = "Aikovdp"
    website = "https://rokucraft.com"
    depend = listOf("Vault")
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    shadowJar {
        archiveClassifier = ""
        listOf(
            "org.incendo.cloud",
            "io.leangen.geantyref"
        ).forEach { relocate(it, "${rootProject.group}.${rootProject.name}.lib.$it") }
    }

    runServer {
        minecraftVersion("1.19.4")
        plugins {
//            github("MilkBowl", "Vault", "1.7.3", "Vault.jar")
//            modrinth("essentialsx", "2.20.2")
        }
    }
}
