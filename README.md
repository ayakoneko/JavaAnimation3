# 🎮 JavaAnimation3

This repository contains lab work for **Object-Oriented Software Development** using JavaFX.  
Each lab builds upon the previous one, enhancing the JavaAnimation application with new features, improved design, and better software engineering practices.  

👉 Visit my lab work: [JavaAnimation](https://github.com/ayakoneko/JavaAnimation3)

---

## 📚 Labs Overview

### 🔹 Lab 3 – Application Enhancements
The JavaAnimation application was extended with new UI elements, configuration options, and improved gameplay controls.  

**Key Features Added:**
- **Main Screen**
- **Configuration Screen**
  - Added interactive controls:
    - Checkbox: *Enable Shadow*.  
    - Radio buttons: *Red*, *Green*, *Blue*.  
    - Slider (5–20) to adjust ball size dynamically (updates label).  
- **Game Integration**
  - All settings affect the game directly (color, shadow effect, size).  
  - Arrow key controls updated:
    - ⬅ Left: decrease speed (X direction).  
    - ➡ Right: increase speed (X direction).  
    - ⬆ Up: increase speed (Y direction).  
    - ⬇ Down: decrease speed (Y direction). 

---

### 🔹 Lab 4 – Refactoring & UI Improvements
Focused on enhancing the UI design and improving software structure using SOLID principles.  

**Key Improvements:**
- **UI Enhancements**
  - Introduced `styles.css` for a more polished look.  
- **Exit Confirmation**
  - Added a dialog to confirm before quitting, triggered by the Exit button or window close.  
- **Code Refactoring (SOLID Principles)**
  - Extracted logic from `Main.java` into separate classes.  
  - Introduced a `Screen` interface; each screen (`MainScreen`, `GameScreen`, `ConfigureScreen`) implements it.  
  - Created a `Frame` interface to decouple `Main` from individual screen implementations.  
  - Added a `Game` class under the `model` package to manage game data.  
  - Added `ScreenWithGame` interface for screens that need access to game data.  

---

### 🔹 Lab 5 – UI Redesign & GRASP Principles
Improved layout and refactored using GRASP design principles.  

**Key Features:**
- Redesigned `GameScreen` layout with game area(board) at top, Back button at bottom.  
- Game area enclosed in a visible rectangle with balanced margins.  
- Applied GRASP principles:  
  - **Information Expert**: Moved ball data and logic to `Game`.  
  - **Creator**: Added `GamePane` to handle visualization.  
  - **Controller**: Added `GameController` to handle input.  

---

### 🔹 Lab 6 – Sprite System
Introduced a flexible sprite system with Factory and Singleton patterns.  

**Key Features:**
- Created `Sprite` interface with `getNode()` and `setXY()`.  
- Implemented `StarSprite` (visual star).  
- Added `SpriteFactory` (Singleton + Factory pattern) for sprite creation.  
- Updated `GamePane` to display 10 randomly positioned stars at game start.  
- Created UML diagram showing relationships between Sprite system components.  
- Improved ball movement responsiveness.  

---

### 🔹 Lab 7 – Entities & Frame-Rate Independent Movement
Shifted towards an entity-based architecture with time-based updates.  

**Key Features:**
- Added `GameConfig` (Singleton) to manage configuration.  
- Created `GameEntity` abstract class with `Player` and `Food` subclasses.  
- Used `process()` method for frame-rate independent movement.  
- Linked `Sprites` with `Entities`.  
- Updated `SpriteFactory` as a middle layer between Game and GamePane.  
- Added entity life cycles (eternal, limited, expired).  
- Introduced `PlayerSprite` and `Food` linked to `StarSprite`.  
- Food items with random lifespan (5–15s), some stationary, some moving.  

---

### 🔹 Lab 8 – Gameplay Mechanics & Audio
Added generics to sprites, player life system, decorator pattern, and audio functions.  

**Key Features:**
- Updated `Sprite` interface with generics.  
- Player starts with 10s life; eating food adds +5s and shows flying “+1”.  
- Applied **Decorator pattern** for low-life display.  
- Game over when life reaches 0.  
- Added audio with **Facade pattern**:  
  - Background music.  
  - Sound effects for eating, dying, and spawning.  

---

### 🔹 Lab 9 – Testing with JUnit & Mockito
Introduced automated testing with JUnit 5 and test doubles.  

**Key Features:**
- `GameControllerTest`: Used Spy double to verify key press actions.  
- `GameEntityTest`:  
  - Tested `isDead()` and `isVisible()` with parameterized tests.  
  - Verified getters/setters.  
- `MessageSpriteTest`: Used Mockito to mock messages and verify UI integration.  
- JavaFX setup in tests to avoid runtime errors.  

---

## 🛠️ Technologies
- Java  
- JavaFX  
- CSS (UI styling)  
- JUnit 5 (Testing)  
- Mockito (Mocking)  

---

## 🚀 How to Run
```bash
# Clone the repo
git clone https://github.com/ayakoneko/JavaAnimation3.git
cd JavaAnimation3

# Open in your IDE (e.g., IntelliJ or Eclipse) and run Main.java / mvn clean javafx:run
