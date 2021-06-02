plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("tz.co.asoft.library")
    id("io.codearte.nexus-staging")
    signing
}

kotlin {
    android { library() }
    jvm { library() }
    js(IR) { library() }
    val darwinTargets = listOf(
        macosX64(),
        iosArm32(),
        iosX64(),
        iosArm64(),
        watchosArm64(),
        watchosArm32(),
        watchosX86(),
        tvosArm64(),
        tvosX64()
    )

    val linuxTargets = listOf(
        linuxX64()
    )

    val windowsTargets = listOf(
        mingwX64()
    )

    sourceSets {

        val commonMain by getting {

        }

        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
            }
        }

        val nativePosixMain by creating {
            dependsOn(commonMain)
        }

        (darwinTargets + linuxTargets).forEach {
            val main by it.compilations.getting {}
            main.defaultSourceSet {
                dependsOn(nativePosixMain)
            }
        }
    }
}

aSoftOSSLibrary(
    version = vers.asoft.krypto,
    description = "A multiplatform library for cyrptography"
)