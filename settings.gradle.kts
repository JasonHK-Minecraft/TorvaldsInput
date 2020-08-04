@file:Suppress("PropertyName")

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")

        maven {
            url = uri("https://dl.bintray.com/nokeedev/distributions")
        }
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
                }
                else -> {
                    if (requested.id.id.startsWith("dev.nokee."))
                    {
                        useModule("${requested.id.id}:${requested.id.id}.gradle.plugin:0.4.0")
                    }
                }
            }
        }
    }
}

// rootProject.name = "TorvaldsInput"

val NativesVariants = listOf(
        "2.0-2.9")

val TorvaldsInputVariants = listOf(
        "1.11.2",
        "1.12.2")

//include(":natives")
//project(":natives").apply {
//    projectDir = file("natives")
//    buildFileName = "build.include.gradle.kts"
//}
//
//NativesVariants.forEach { variant ->
//    include(":natives:$variant")
//    project(":natives:$variant").apply {
//        projectDir = file("natives/$variant")
//    }
//}

TorvaldsInputVariants.forEach { variant ->
    include(":$variant")
    project(":$variant").apply {
        projectDir = file("versions/$variant")
        buildFileName = "../build.common.gradle"
    }
}
