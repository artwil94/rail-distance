# Rail Distance

## Overview

Rail Distance is an Android application that allows users to find and calculate the distance between train stations. The application fetches train station data from an API, filters the data, converts Polish characters to their standard equivalents, and calculates the distance between selected stations.

## Features

- **Fetch Train Stations**: Retrieves a list of train stations from a remote API.
- **Filter Stations**: Allows users to filter the list of stations based on keywords.
- **Character Conversion**: Converts Polish characters in station names to their standard equivalents.
- **Distance Calculation**: Calculates the distance between two selected train stations and displays the result.

## Installation

To set up the project locally, follow these steps:

1. **Clone the repository**:
    ```bash
    git clone https://github.com/yourusername/rail-distance.git
    cd rail-distance
    ```

2. **Open the project**: Open the project in Android Studio.

3. **Sync the project**: Ensure that all Gradle dependencies are downloaded and the project is synced successfully.

4. **Run the app**: Connect an Android device or start an emulator and run the app from Android Studio. (Application works offline, but requires download data from API at least once, so network connection needed)

## Usage

1. **Fetch Stations**: Open the app and fetch the list of train stations from the API.
2. **Select Stations**: Select two train stations to calculate the distance between them.
3. **Calculate Distance**: The app will display the calculated distance in kilometers.

## Technology Stack

- **Kotlin**: Programming language used for the application.
- **Android Architecture Components**: MVVM pattern
- **Retrofit**: For making API calls.
- **Room**: For local database storage.
- **Mockito**: For mocking dependencies in unit tests.
- **Timber**: For logging.
