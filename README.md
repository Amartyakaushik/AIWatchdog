# AIWatchdog - AI Safety Incident Tracker

A modern Android app for tracking, reporting, and viewing AI safety incidents, built as a take-home assignment for Sparklehood.

---

##  How to Build and Run

1. **Clone the repository**
   ```sh
   git clone <https://github.com/Amartyakaushik/AIWatchdog.git>
   cd AIWatchdog
   ```

2. **Open in Android Studio**
    - Open Android Studio (latest stable recommended)
    - Select `Open an existing project` and choose the `AIWatchdog` folder

3. **Install Dependencies**
    - Gradle and dependencies will sync automatically.
    - If prompted, click "Sync Now" in the IDE.

4. **Build and Run**
    - Connect an Android device or start an emulator (API 24+)
    - Click the green "Run" button or use `Shift+F10`

---

## Tech Stack

- **Language:** Kotlin
- **Framework:** Android (Material Components, Jetpack Navigation, ViewModel, LiveData)
- **Min SDK:** 24
- **Target SDK:** 35

---

## Features

- **Incident Dashboard:** View, filter, and sort AI safety incidents.
- **Detail View:** Tap any incident to see full details in a polished UI.
- **Report Incident:** Add new incidents with severity, title, and description.
- **Bottom Navigation:** Easily switch between Home and Create Alert.
- **Material Design:** Responsive, modern, and accessible UI.
- **Dark/Light Theme:** Uses Material theming for compatibility.

---

## Design Decisions & Challenges

- **Navigation:** Used Jetpack Navigation Component for robust, type-safe navigation and bottom navigation integration.
- **State Management:** Shared `IncidentViewModel` via `activityViewModels()` for consistent data across fragments.
- **UI Polish:** Focused on Material Components, color selectors, and responsive layouts for a professional look.
- **Date Handling:** Used `SimpleDateFormat` for compatibility with all API levels.
- **Error Handling:** Addressed common Android navigation and layout pitfalls (e.g., NavHostFragment, fragment container constraints).
- **Customization:** Severity chips use icons and color selectors for clarity and accessibility.

---

## Screenshots
---
![SplashScreen.jpg](..%2F..%2FOneDrive%2FDesktop%2FSemester%20Courses%2FDRIVE%2FSplashScreen.jpg)
![IncidentList.jpg](..%2F..%2FOneDrive%2FDesktop%2FSemester%20Courses%2FDRIVE%2FIncidentList.jpg)
![Description.jpg](..%2F..%2FOneDrive%2FDesktop%2FSemester%20Courses%2FDRIVE%2FDescription.jpg)
![AddAlert.jpg](..%2F..%2FOneDrive%2FDesktop%2FSemester%20Courses%2FDRIVE%2FAddAlert.jpg)

## Notes

- All data is stored in memory (no backend or database).
- The app is designed for demonstration and evaluation purposes.

---

**Thank you for reviewing! If you have any questions, feel free to reach out.**