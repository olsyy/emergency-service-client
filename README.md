# 📢 Emergency Service Officer Client App

![Kotlin](https://img.shields.io/badge/kotlin-2.0.10-orange.svg)
![Apache 2](https://img.shields.io/badge/license-Apache2-green.svg?style=flat)

## 📓 Description
This Android application is designed for officers to manage and view emergency incidents and zones. It uses MVVM architecture, offering two main sections: Zones and Incidents. The app allows officers to view detailed information about zones, incidents, and update the status of incidents. The UI is built using Jetpack Compose.

## 🏁 Start
1. Clone the repository:
```bash
git clone https://github.com/yourusername/emergency-service-client.git
```
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Build the app.
5. Run [the Server](https://github.com/lsyyx/emergency-api).
6. Run the app.

## 🏠 Architecture
- **Kotlin**: Programming language used for development.
- **MVVM**: Model-View-ViewModel architecture for separating UI from business logic.
- **Hilt**: DI library to simplify the management of dependencies.
- **Jetpack Compose**: Modern Android UI toolkit for building UIs in a declarative way.
- **Modularization**: The app is structured into modules for scalability and maintainability.
- **Clean Architecture**: Follows principles for creating well-structured and testable applications.

## 💡 Usage
The app includes two primary tabs:
- Zones Tab: Displays a list of zones with names and associated colors indicating incident probability. Tap a zone to view details, including incidents within that zone.
- Incidents Tab: Displays a list of all incidents with types and statuses. Tap an incident to view its details and change its status.
  
![App work](https://github.com/lsyyx/emergency-service-client/blob/main/img/Screen_recording.gif)

## License
This project is licensed under the Apache 2.0 License - see the [LICENSE](https://github.com/lsyyx/emergency-service-client/blob/main/LICENSE) file for details.
