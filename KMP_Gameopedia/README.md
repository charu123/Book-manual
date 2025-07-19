# 🎮 GameOpedia - Kotlin Multiplatform Game Discovery App

A modern Kotlin Multiplatform (KMP) application for discovering and exploring video games. Built with Compose Multiplatform and Ktor HTTP client.

## 🚀 Features

- **Game Discovery**: Browse popular games with detailed information
- **Search Functionality**: Search for games by title with real-time results
- **Rich Game Details**: View game ratings, platforms, genres, and descriptions
- **Modern UI**: Beautiful Material Design 3 interface
- **Cross-Platform**: Runs on Android, Desktop, and iOS (when iOS tools are available)
- **Robust HTTP Client**: Ktor-based networking with proper timeout handling and error management

## 🛠️ Technology Stack

- **Kotlin Multiplatform**: Shared code across platforms
- **Compose Multiplatform**: Modern declarative UI framework
- **Ktor Client**: HTTP networking with timeout and error handling
- **Kotlinx Serialization**: JSON parsing and data serialization
- **Material Design 3**: Modern and accessible UI components
- **Coroutines & Flow**: Async programming and reactive data streams

## 🏗️ Architecture

The app follows a clean architecture pattern:

- **UI Layer**: Compose UI components in `src/commonMain/kotlin/org/example/project/ui/`
- **ViewModel**: State management in `src/commonMain/kotlin/org/example/project/viewmodel/`
- **Repository**: Data access layer in `src/commonMain/kotlin/org/example/project/repository/`
- **Network**: HTTP client and services in `src/commonMain/kotlin/org/example/project/network/`
- **Data Models**: Serializable data classes in `src/commonMain/kotlin/org/example/project/data/`

## 📦 Key Components

### HTTP Client Configuration
- **Timeout Management**: 30-second request timeout, 15-second connect timeout
- **Error Handling**: Comprehensive error handling with user-friendly messages
- **Logging**: Network request/response logging for debugging
- **Content Negotiation**: JSON serialization with Kotlinx Serialization

### Game Service
- Mock data service simulating real API calls
- Realistic game data with ratings, platforms, and genres
- Search functionality with case-insensitive filtering
- Popular games and search results

### Repository Pattern
- Caching mechanism to reduce unnecessary network calls
- Reactive data flow using Kotlin Flow
- Error handling with Result sealed class
- Clean separation between data sources and UI

## 🔧 Build Requirements

- **Android SDK**: API 24+ (Android 7.0+)
- **Java/JDK**: Java 11 or higher
- **Kotlin**: 2.2.0
- **Gradle**: 8.7+

## 🚀 Getting Started

### 1. Clone and Setup
```bash
# Extract the project
unzip KMP_Gameopedia.zip
cd KMP_Gameopedia

# Set Android SDK path (if needed)
export ANDROID_HOME=/path/to/android-sdk
```

### 2. Build the Project
```bash
# Clean build (recommended)
./gradlew clean build

# Build just Android APK
./gradlew assembleDebug

# Build without tests (if experiencing test issues)
./gradlew assembleDebug -x test -x lint
```

### 3. Run the Application

#### Android
```bash
# Install and run on connected device/emulator
./gradlew installDebug
```

#### Desktop
```bash
# Run desktop application
./gradlew :composeApp:run
```

## 🎯 Usage

1. **Browse Popular Games**: The app starts by showing popular games
2. **Search Games**: Use the search bar to find specific games
3. **View Details**: Tap on any game card to see detailed information
4. **Refresh**: Pull to refresh or use the retry button on errors

## 🔧 Troubleshooting

### Common Issues

1. **Android SDK not found**:
   ```bash
   export ANDROID_HOME=/path/to/android-sdk
   echo "sdk.dir=$ANDROID_HOME" > local.properties
   ```

2. **Build fails with timeout errors**:
   - The HTTP client is configured with proper timeouts
   - Mock data is used to prevent actual network timeout issues
   - Check internet connection for dependency downloads

3. **Gradle build issues**:
   ```bash
   ./gradlew clean
   ./gradlew build --refresh-dependencies
   ```

4. **iOS build on Linux**:
   - iOS targets are commented out for Linux environments
   - Uncomment iOS targets when building on macOS with Xcode

## 📱 Demo Data

The app includes rich mock game data featuring:
- Popular games like "The Legend of Zelda: Breath of the Wild"
- Various genres (Adventure, RPG, Action, etc.)
- Multiple platforms (Nintendo Switch, PC, PlayStation, Xbox)
- Realistic ratings and review counts
- Engaging game descriptions

## 🔄 HTTP Client Features

### Timeout Configuration
```kotlin
install(HttpTimeout) {
    requestTimeoutMillis = 30_000L // 30 seconds
    connectTimeoutMillis = 15_000L // 15 seconds  
    socketTimeoutMillis = 30_000L  // 30 seconds
}
```

### Error Handling
- Network timeout handling
- Connection error recovery
- User-friendly error messages
- Retry mechanisms

### Platform-Specific Clients
- **Android**: OkHttp-based client
- **Desktop**: Java HTTP client
- **iOS**: Darwin native client (when available)

## 🎨 UI/UX Features

- **Material Design 3**: Modern design system
- **Dark/Light Theme**: Automatic system theme support
- **Responsive Layout**: Adapts to different screen sizes
- **Loading States**: Proper loading indicators
- **Error States**: Clear error messages with retry options
- **Empty States**: Informative empty state messages

## 📁 Project Structure

```
KMP_Gameopedia/
├── composeApp/
│   ├── src/
│   │   ├── commonMain/kotlin/org/example/project/
│   │   │   ├── ui/components/          # Reusable UI components
│   │   │   ├── viewmodel/              # ViewModels and UI state
│   │   │   ├── repository/             # Data repositories
│   │   │   ├── network/                # HTTP client and services
│   │   │   ├── data/                   # Data models
│   │   │   └── App.kt                  # Main application
│   │   ├── androidMain/                # Android-specific code
│   │   ├── desktopMain/                # Desktop-specific code
│   │   └── commonTest/                 # Shared tests
│   └── build.gradle.kts                # Module build configuration
├── gradle/
│   └── libs.versions.toml              # Dependency versions
├── build.gradle.kts                    # Project build configuration
├── settings.gradle.kts                 # Project settings
└── README.md                           # This file
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if needed
5. Submit a pull request

## 📄 License

This project is open source and available under the MIT License.

## 🙏 Acknowledgments

- **JetBrains**: For Kotlin Multiplatform and Compose Multiplatform
- **Ktor**: For the excellent HTTP client library
- **Material Design**: For the beautiful design system

---

**Happy Gaming! 🎮**