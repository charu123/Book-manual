plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()
    jvm()
    
    // Commented out iOS targets for Linux environment
    // listOf(
    //     iosX64(),
    //     iosArm64(),
    //     iosSimulatorArm64()
    // ).forEach {
    //     it.binaries.framework {
    //         baseName = "CoreNetwork"
    //         isStatic = true
    //     }
    // }
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.kotlinx.serialization)
        }
        
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        
        // iosMain.dependencies {
        //     implementation(libs.ktor.client.darwin)
        // }
        
        jvmMain.dependencies {
            implementation(libs.ktor.client.java)
                  }
      }
  }
  
  android {
      namespace = "org.example.project.core.network"
      compileSdk = libs.versions.android.compileSdk.get().toInt()
      
      defaultConfig {
          minSdk = libs.versions.android.minSdk.get().toInt()
      }
      compileOptions {
          sourceCompatibility = JavaVersion.VERSION_11
          targetCompatibility = JavaVersion.VERSION_11
      }
  }