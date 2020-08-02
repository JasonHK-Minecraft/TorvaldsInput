buildscript {}

plugins {
    id("com.replaymod.preprocess") version "3c46acb"
//    id("cross-versioned-mod-plugin")
}

subprojects {
    buildscript {
        repositories {
            maven("https://jitpack.io")
        }
    }
}

repositories {
    mavenCentral()
    jcenter()
}

//configurations {
//    // give tests access to compileOnly dependencies
//    getByName("testImplementation").extendsFrom(getByName("compileOnly"))
//}

//dependencies {
//    compileOnly("systems.manifold:manifold-preprocessor:2020.1.20")
//    testCompile("junit:junit:4.12")
//
//    // Add manifold to -processorpath for javac
//    annotationProcessor("systems.manifold:manifold-preprocessor:2020.1.20")
//}

group = "net.jasonhk.minecraft.mods"
version = "0.0.1"

//configure<JavaPluginConvention>() {
//    sourceCompatibility = JavaVersion.VERSION_1_8
//    targetCompatibility = JavaVersion.VERSION_1_8
//}

//if ((JavaVersion.current() != JavaVersion.VERSION_1_8) &&
//    sourceSets.getByName("main").allJava.any { it.name == "module-info.java" })
//{
//    tasks.withType(JavaCompile::class.java)
//    {
//        options.compilerArgs.addAll(listOf(
//                "-Xplugin:Manifold", "--module-path", classpath.asPath))
//    }
//}
//else
//{
//    tasks.withType(JavaCompile::class.java)
//    {
//        options.compilerArgs.add("-Xplugin:Manifold")
//    }
//}

preprocess {
    "1.12.2"(11202, "srg") {
        "1.11.2"(11102, "srg"/*, file("versions/1.12.2/mapping.txt")*/)
    }
}

//if (JavaVersion.current() != JavaVersion.VERSION_1_8 &&
//    sourceSets.main.allJava.files.any {it.name == "module-info.java"}) {
//    tasks.withType(JavaCompile) {
//        // if you DO define a module-info.java file:
//        options.compilerArgs += ['-Xplugin:Manifold', '--module-path', it.classpath.asPath]
//    }
//} else {
//    tasks.withType(JavaCompile) {
//        // If you DO NOT define a module-info.java file:
//        options.compilerArgs += ['-Xplugin:Manifold']
//    }
//}
