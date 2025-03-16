plugins {
    id("java")
}

allprojects {
    apply(plugin = "java-library")

    group = "de.smoofy.core"
    version = project.version

    repositories {
        mavenCentral()

        maven("https://repo.papermc.io/repository/maven-public/")
        maven("https://nexus.velocitypowered.com/repository/maven-public/")
    }

    dependencies {
        implementation(rootProject.libs.paper)

        annotationProcessor(rootProject.libs.lombok)
        compileOnly(rootProject.libs.lombok)
    }

    tasks.withType<JavaCompile>().configureEach {
        options.encoding = "UTF-8"
        sourceCompatibility = JavaVersion.VERSION_21.toString()
        targetCompatibility = JavaVersion.VERSION_21.toString()
    }
}