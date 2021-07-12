# Asteroid Radar App

An application that fetches and displays data of nearby asteroids from the NASA NeoWs API, using Retrofit, Picasso, Room and WorkManager.

The app consists of two views:
- a home screen with a list of nearby asteroids for the next seven days and an Image of the Day, both loaded from the NASA API.
- an asteroid detail screen, showing full details of the selected asteroid with an image that indicates whether the asteroid is potentially hazardous.

Use of this app requires a NASA API key (sign up at (https://api.nasa.gov)[https://api.nasa.gov/]). Assign the API key to a property named `apiKey` in the `local.properties` file.
## Technical features
- fetches nearby asteroid data from the API for the next seven days using Retrofit
- caches and stores the data in a local Room database
- uses WorkManager to schedule background work to fetch and store the latest data for offline use
- displays a list of the data using RecyclerView
- navigates to a details view using Jetpack Navigation
- fetches and displays an Image of the Day on the app homepage with Picasso