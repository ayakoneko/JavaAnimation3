1. S - Single Responsibility
    - Main coordinates screens and lifecycle
    - Game holds only game state/logic
    - Each Screen builds only its own UI
   
2. O - Open/Closed
    - New screens can be added by implementing Screen interface without modifying existing logic
   
3. L - Liskov Substitution
    - Any Screen can be swapped where a Screen is expected, ScreenWithGame extends behaviour cleanly
   
4. I - Interface
    - Screen vs ScreenWithGame prevents unrelated screens from depending on Game
   
5. D - Dependency Inversion
    - Screens depends on the Frame interface, not the concrete Main
    - Model (Game) is injected into screens rather than constructed internally