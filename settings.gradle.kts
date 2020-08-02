pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://maven.fabricmc.net")
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "com.replaymod.preprocess" -> {
                    useModule("com.github.replaymod:preprocessor:${requested.version}")
                }
            }
        }
    }
}

// rootProject.name = "TorvaldsInput"

val TorvaldsInputVersions = listOf(
        "1.11.2",
        "1.12.2")

TorvaldsInputVersions.forEach { version ->
    include(":$version")
    project(":$version").apply {
        projectDir = file("versions/$version")
        buildFileName = "../build.common.gradle"
    }
}
