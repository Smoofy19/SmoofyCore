import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

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

    implementation(libs.packetevents)
    implementation(libs.entitylib)

    implementation(libs.evelon)
}

tasks.shadowJar {
    archiveBaseName.set("Core")
    archiveClassifier.set("")
    manifest {
        attributes["paperweight-mappings-namespace"] = "spigot"
    }
}

tasks.assemble {
    dependsOn(tasks.reobfJar)
}

paper {
    name = "Core"
    version = project.version.toString()
    author = "Smoofy"
    apiVersion = "1.21"
    main = "de.smoofy.core.base.bootstrap.PaperBootstrap"

    load = BukkitPluginDescription.PluginLoadOrder.POSTWORLD
}