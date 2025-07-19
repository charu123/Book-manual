plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
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
    //         baseName = "Game"
    //         isStatic = true
    //     }
    // }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.coreNetwork)
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle)
            implementation(libs.androidx.lifecycle.compose)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.datetime)
                  }
      }
  }
  
  android {
      namespace = "org.example.project.game"
      compileSdk = libs.versions.android.compileSdk.get().toInt()
      
      defaultConfig {
          minSdk = libs.versions.android.minSdk.get().toInt()
      }
      compileOptions {
          sourceCompatibility = JavaVersion.VERSION_11
          targetCompatibility = JavaVersion.VERSION_11
      }
  }