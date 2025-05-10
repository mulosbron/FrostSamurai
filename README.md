# Frost Samurai

## Overview
An AR-based educational Android game designed for children, combining entertainment with learning through augmented reality experiences. Players catch snowflakes while avoiding tree branches, earning virtual currency (Shuriken) that can be used in an in-game marketplace.

## Purpose
To create an engaging educational platform that leverages AR technology to help children develop spatial intelligence and hand-eye coordination skills while learning about symmetric and asymmetric shapes.

### Key Objectives:
- Provide an educational AR gaming experience for children
- Develop visual-spatial intelligence through interactive play
- Implement a reward system that encourages continued learning
- Create a safe, child-friendly environment with parental controls

## Scope

### Technology Stack:
- **Mobile Development**: Android (Kotlin)
- **AR Framework**: SceneView AR, Google ARCore
- **Authentication**: Firebase Authentication
- **Database**: Firebase
- **Web**: HTML, CSS, JavaScript
- **3D Models**: GLB format

### Project Features:
- AR-based gameplay with 3D objects
- User authentication system
- In-game currency (Shuriken) based on score
- Virtual marketplace for purchasing educational content
- Responsive web landing page
- Educational packages for different learning levels
- Symmetric and asymmetric shape recognition

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

### Key Components:
1. **AR Game (ARFragment.kt)**
   - 3D object spawning and movement
   - Score and lives system
   - Touch interaction handling
   - Shuriken currency calculation

2. **Authentication (LoginFragment.kt)**
   - Firebase email/password authentication
   - User session management
   - Auto-login functionality

3. **Marketplace (MarketFragment.kt)**
   - Virtual currency display
   - Educational package purchasing
   - Symmetric/asymmetric shape unlocks

4. **Web Interface**
   - Child-friendly design
   - Parent information section
   - Responsive mobile layout

### Development Process:
1. **Planning Phase**: Requirements analysis, SWOT analysis
2. **Design Phase**: UML diagrams, UI/UX design
3. **Development Phase**: AR implementation, Firebase integration
4. **Testing Phase**: User testing, performance optimization
5. **Deployment Phase**: Play Store preparation, web hosting

## Screenshots

### Game Screenshot
<img width="500" alt="Game Screenshot" src="https://github.com/user-attachments/assets/b54b698f-f88d-4043-ad89-4d566fdf68f3">

### AR Gameplay (GIF)
<img src="https://github.com/user-attachments/assets/06a3669f-8a30-428a-8120-b9b82a48cd80" width="400" alt="AR Gameplay GIF" />
