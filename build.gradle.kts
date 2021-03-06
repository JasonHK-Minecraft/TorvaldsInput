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

apply(plugin = "com.replaymod.preprocess")

repositories {
    mavenCentral()
    jcenter()
}

group = "net.jasonhk.minecraft.mods"
version = "0.0.1"

preprocess {
    "1.14.4-Forge"(11404, "srg") {
        "1.13.2"(11302, "srg") {
            "1.12.2"(11202, "srg", file("variants/1.13.2/mapping.txt")) {
                "1.12.1"(11201, "srg") {
                    "1.12"(11200, "srg") {
                        "1.11.2"(11102, "srg") {
                            "1.11"(11100, "srg") {
                                "1.10.2"(11002, "srg") {
                                    "1.10"(11000, "srg") {
                                        "1.9.4"(10904, "srg") {
                                            "1.9"(10900, "srg")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
