import net.minecrell.pluginyml.paper.PaperPluginDescription

plugins {
    alias(libs.plugins.shadow)
    alias(libs.plugins.plugindescription)
    alias(libs.plugins.paperweight)
}

repositories {
    maven("https://repo.codemc.io/repository/maven-releases/")
    maven("https://maven.evokegames.gg/snapshots")

    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation(project(":core-api"))

    paperweight.paperDevBundle(libs.versions.paper)

    compileOnly(libs.velocity)

    implementation(libs.evelon)
}

tasks.shadowJar {
    archiveFileName.set("Core-${project.version}.jar")
    manifest {
        attributes["paperweight-mappings-namespace"] = "spigot"
    }
}

tasks.reobfJar {
    dependsOn(tasks.shadowJar)
}

paper {
    name = "Core"
    version = project.version.toString()
    author = "Smoofy"
    apiVersion = "1.21"
    main = "de.smoofy.core.base.bootstrap.PaperBootstrap"
    serverDependencies {
        register("LuckPerms") {
            load = PaperPluginDescription.RelativeLoadOrder.BEFORE
        }
    }
}