```text
app/
├── MainActivity.kt
├── MovieApplication.kt
└── navigation/         # Handles app-level navigation between screens

core/
├── data/
│   ├── di/                             # Provides repositories and mappers
│   ├── mapper/                         # Converts data layer models to domain models
│   └── repositoryImplementation/       # Implements domain repositories using data sources
│
├── designTheme/
│   ├── components/                     # Shared UI components (e.g., RatingStars, ReviewItem)
│   └── theme/                          # MaterialTheme definitions
│
├── domain/
│   ├── di/                             # Provides domain-level use cases
│   ├── domainModels/                   # Domain models (used by UI layer)
│   ├── repository/                     # Repository interfaces used in Use Cases
│   ├── shared/                         # Shared logic, e.g., FavoritesManager
│   └── usecase/                        # Use cases (business logic per feature)
│
└── network/
    ├── di/                             # Retrofit and network service provisioning
    ├── models/                         # API response models (DTOs)
    └── services/                       # Retrofit interfaces

feature/
├── home/
│   └── HomeScreen.kt                   # Home screen UI and logic
│
└── details/
    ├── DetailsScreen.kt                # Details screen UI
    └── DetailsViewModel.kt             # Handles fetching movie details, reviews, similar movies...
```


![Screenshot_20250520_231526](https://github.com/user-attachments/assets/e41ca573-6442-40b4-aeb6-1a4461fe4bb5)
![Screenshot_20250520_231724](https://github.com/user-attachments/assets/9af2673c-ba46-4d9d-953c-46be2cecfef1)
