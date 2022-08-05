plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

kotlin {
    android()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.ktorCore)
                implementation(Libraries.ktorLogging)
                implementation(Libraries.ktorSerialization)
                implementation(Libraries.ktorContentNegotiation)
                implementation(Libraries.Common.sqlDelight)
                implementation(Libraries.Common.sqlDelightExtension)
                implementation(Libraries.Common.kotlinxSerializationCore)
                implementation(Libraries.Common.kotlinxCoroutinesCore)
                implementation(Libraries.koinCore)

                api(Libraries.napier)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Libraries.Android.sqlDelight)
                implementation(Libraries.ktorAndroid)
                implementation(Libraries.coroutinesAndroid)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies {
                implementation(Libraries.IOs.ktorClient)
                implementation(Libraries.IOs.sqlDelight)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 22
        targetSdk = 32
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "com.helloanwar.kmmmvi.shared.data_cache.sqldelight"
        sourceFolders = listOf("kotlin")
    }
}