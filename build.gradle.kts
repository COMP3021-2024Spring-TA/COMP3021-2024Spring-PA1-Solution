@file:Suppress("SpellCheckingInspection")

import proguard.gradle.ProGuardTask

plugins {
    java
    application
    checkstyle
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "hk.ust.comp3021"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(18))
    }
}

application {
    mainClass.set("hk.ust.comp3021.ASTManager")
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("com.guardsquare:proguard-gradle:7.2.2")
    }
}

dependencies {
    testImplementation("org.jetbrains:annotations:23.0.0")
    compileOnly("org.jetbrains:annotations:23.0.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
    testImplementation("org.mockito:mockito-core:4.8.0")
    testImplementation("org.apache.commons:commons-collections4:4.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

checkstyle {
    toolVersion = "10.3.2"
    sourceSets = setOf(project.sourceSets.main.get())
}

tasks {
    withType<JavaCompile> {
        options.compilerArgs = listOf("--enable-preview")
        options.encoding = "UTF-8"
        sourceCompatibility = "18"
        targetCompatibility = "18"
    }
    withType<Javadoc> {
        (options as? CoreJavadocOptions)?.apply {
            addStringOption("source", java.toolchain.languageVersion.get().toString())
            addBooleanOption("-enable-preview", true)
        }
    }
    withType<JavaExec> {
        standardInput = System.`in`
        jvmArgs(
            "--enable-preview",
        )
    }
    withType<Jar> {
        manifest {
            attributes.apply {
                this["Main-Class"] = application.mainClass.get()
            }
        }
    }
    withType<JacocoReport> {
        dependsOn(test)

        reports {
            xml.required.set(false)
            csv.required.set(false)
            html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
        }
    }
    withType<Test> {
        group = "verification"

        useJUnitPlatform()

        systemProperties(
            "junit.jupiter.execution.timeout.testable.method.default" to "2000 ms"
        )

        jvmArgs("--enable-preview")
    }

    withType<Checkstyle> {

    }

    create<ProGuardTask>("proguard") {
        injars(jar.flatMap { it.archiveFile })
        outjars(jar.flatMap { it.destinationDirectory.file("${project.name}-proguard.jar") })

        libraryjars("${System.getProperty("java.home")}/jmods")
        libraryjars(sourceSets.main.map {
            (it.compileClasspath + it.runtimeClasspath).distinct() - jar.flatMap { jar -> jar.archiveFile }.get().asFile
        })

        keep("public class hk.ust.comp3021.ASTManager { public static void main(java.lang.String[]); }")

        optimizations("!class/merging/horizontal")

        printmapping(jar.flatMap { it.destinationDirectory.file("${project.name}-proguard-mapping.txt") })
        overloadaggressively()
        flattenpackagehierarchy()
        allowaccessmodification()
        mergeinterfacesaggressively()
        dontskipnonpubliclibraryclassmembers()
        useuniqueclassmembernames()
        optimizationpasses(5)
    }
    
    val hiddenTest by creating(Test::class) {
        useJUnitPlatform {
            includeTags("hidden")
        }
        testClassesDirs = sourceSets["test"].output.classesDirs
        classpath = sourceSets["test"].runtimeClasspath

        // Configure test logging
        testLogging {
            events("passed", "skipped", "failed")
//            showStandardStreams = true
//            exceptionFormat = TestExceptionFormat.FULL
        }
    }


    val publicTest by creating(Test::class) {
        useJUnitPlatform {
            includeTags("public")
        }
        testClassesDirs = sourceSets["test"].output.classesDirs
        classpath = sourceSets["test"].runtimeClasspath

        // Configure test logging
        testLogging {
            events("passed", "skipped", "failed")
//            showStandardStreams = true
//            exceptionFormat = TestExceptionFormat.FULL
        }
    }
    
    
}

tasks.test {
    // Conditionally disable the test task based on a project property
    onlyIf {
        !project.hasProperty("skipTests")
    }
}


