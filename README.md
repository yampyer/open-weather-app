# open-weather-app

This project consumes an external API ```https://openweathermap.org/current```, it uses a Clean Architecture to follow the SOLID principles.

### It also uses:

- Retrofit to make server calls
- MVVM pattern architecture
- Repository pattern as intermediate for getting the data from a single source, a later implementation can be with Room schemas to add more persistency to app
- Dagger2 for dependency injection
- Coroutines to get suspend responses from API
- Glide for picture loading
- Espresso and Mockito for UI test
