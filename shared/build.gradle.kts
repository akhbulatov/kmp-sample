plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.cocoapods)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    targets.configureEach {
        compilations.configureEach {
            compilerOptions.configure {
                freeCompilerArgs.addAll(
                    "-Xexpect-actual-classes",
                    "-opt-in=kotlinx.cinterop.ExperimentalForeignApi"
                )
            }
        }
    }

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    cocoapods {
        version = "1.0"
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        pod(name = "Pushy") {
            version = "1.0.51"
            extraOpts += listOf("-compiler-option", "-fmodules")
        }
//        pod(name = "GoogleMLKit/BarcodeScanning") {
//            moduleName = "MLKitBarcodeScanning"
//            version = "3.2.0"
//        }
//        pod("GoogleMLKit/Vision") {
//            moduleName = "MLKitVision"
//            version = "3.2.0"
//        }
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        commonMain.dependencies {
            // put your Multiplatform dependencies here
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)

            implementation(libs.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentnegotiation)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.json)
            implementation(libs.serialization.json)
            implementation(libs.sqldelight.coroutines)
            implementation(libs.androidx.datastore.core)
            implementation(libs.voyager.navigator)
            implementation(libs.voyager.screenModel)
            implementation(libs.qrose)
            implementation(libs.napier)

            implementation("dev.icerock.moko:permissions-compose:0.17.0")
        }

        androidMain.dependencies {
            implementation(libs.androidx.activity)
            implementation(libs.androidx.activity.compose)

            implementation(libs.playservices.location)
            implementation(libs.playservices.mlkit.barcodescanning)

            implementation(libs.coroutines.android)
            implementation(libs.coroutines.playservices)
            implementation(libs.ktor.client.android)
            implementation(libs.sqldelight.android.driver)
            implementation(libs.pushy.android)
            implementation(libs.mlkit.barcodescanner.android)

            implementation("androidx.core:core-ktx:1.12.0")
            val camerax_version = "1.3.1"
            implementation("androidx.camera:camera-core:${camerax_version}")
            implementation("androidx.camera:camera-camera2:${camerax_version}")
            implementation("androidx.camera:camera-lifecycle:${camerax_version}")
            implementation("androidx.camera:camera-view:${camerax_version}")
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sqldelight.native.driver)
        }
    }
}

android {
    namespace = "org.example.kmpsample"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "org.example.kmpsample"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    databases {
        create(name = "Database") {
            packageName.set("org.example.kmpsample.shared.database")
        }
    }
}
