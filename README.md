# Hearthstone Card Viewer 📜🃏

A simple Android application that allows users to browse and view details of Hearthstone cards. Powered by the Hearthstone API via [RapidAPI](https://rapidapi.com/).

---

## Features ✨
- **Browse Hearthstone cards** in a RecyclerView.
- **View detailed information** about a selected card, including an enlarged image and its stats.
- Built with **modern Android development best practices**.

---

## Screenshots 📸
*(Add some screenshots here to showcase the app)*

---

## How to Use 🛠️

1. **Clone the repository**:
    ```bash
    git clone https://github.com/marcos-mobdev/kotlin-hearthstone-cards
    ```

2. **Setup API Key**:
    - Create an account or log in at [RapidAPI](https://rapidapi.com/auth/sign-up).
    - Subscribe to the Hearthstone API and obtain your API Key. [HearthstoneAPI](https://rapidapi.com/omgvamp/api/hearthstone)
    - Create a new Kotlin object named `Secret` in your project:

    ```kotlin
    object Secret {
        const val API_KEY = "your-api-key-here"
    }
    ```

    - Replace `"your-api-key-here"` with your actual API key.

3. **Run the app**:
    - Build and run the project in Android Studio.

---

## Technologies Used 🛠️

### Libraries & Tools
- **[Retrofit](https://square.github.io/retrofit/)**: For making HTTP requests.
- **[Picasso](https://square.github.io/picasso/)**: For image loading and caching.
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)**: Lifecycle-aware UI-related data holder.
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata)**: Observable data holder for UI updates.
- **[Hilt](https://dagger.dev/hilt/)**: Dependency injection for Android.

### Architecture
- **MVVM (Model-View-ViewModel)**: Ensures a clean separation of concerns and easy testing.

---

## App Overview 📚

1. **Home Screen**: Displays a list of Hearthstone cards in a RecyclerView.  
   ![RecyclerView Example](path/to/your-image.png) *(Replace with actual image)*

2. **Card Details Screen**: Displays a larger image of the card and its details.  
   ![Details Example](path/to/your-image.png) *(Replace with actual image)*

---

## API Integration 🌐

The app integrates with the **Hearthstone API** via RapidAPI. This allows users to browse and view information about various Hearthstone cards.

### Endpoints Used:
- `/cards/sets/{setCard}`: Retrieves a list of cards of the set.

---

## Contribution 🤝

Contributions are welcome!  
Feel free to open issues or submit pull requests.

---

## License 📄

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
