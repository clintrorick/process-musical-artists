plugins {
    id("org.jetbrains.kotlin.jvm") version "1.5.10"

    application
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("org.clintrorick.ProcessMusicalArtistsKt")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
   testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.30")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.7.0")
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "org.clintrorick.ProcessMusicalArtistsKt"
    }
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })

}