# Frost Samurai

## Overview
An AR-based educational Android game designed for children, combining entertainment with learning through augmented reality experiences. Players catch snowflakes while avoiding tree branches, earning virtual currency (Shuriken) that can be used in an in-game marketplace.

## Purpose
To create an engaging educational platform that leverages AR technology to help children develop spatial intelligence and hand-eye coordination skills while learning about symmetric and asymmetric shapes.

## Scope

### Technology Stack:
- **Mobile Development**: Android (Kotlin)
- **AR Framework**: SceneView AR, Google ARCore
- **Authentication**: Firebase Authentication
- **Database**: Firebase
- **Web**: HTML, CSS, JavaScript
- **3D Models**: GLB format

## Implementation

### Project Structure:
```
FrostSamurai/
├── docs/                          # Project documentation
│   ├── gereksinim analizi/       # Requirements analysis
│   ├── swot/                     # SWOT analysis
│   └── uml diyagramları/         # UML diagrams
├── mobile/                       # Android application
│   └── app/src/main/java/com/mulosbron/frostsamurai/
│       ├── ARFragment.kt         # AR game logic
│       ├── LoginFragment.kt      # User authentication
│       ├── MarketFragment.kt     # In-game store
│       └── MainActivity.kt       # Main activity
├── web/                          # Landing page
│   ├── index.html               # Homepage
│   ├── cocuklar.html            # Children's section
│   ├── ebeveynler.html          # Parents' section
│   └── iletisim.html            # Contact page
└── README.md
```

## Screenshots

### Game Screenshot
<img width="500" alt="Game Screenshot" src="https://github.com/user-attachments/assets/b54b698f-f88d-4043-ad89-4d566fdf68f3">

### AR Gameplay (GIF)
<img src="https://github.com/user-attachments/assets/06a3669f-8a30-428a-8120-b9b82a48cd80" width="400" alt="AR Gameplay GIF" />
