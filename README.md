# Java 2D Game (Tom’s Revenge)

A 2D tile-based game written in **Java**, using **Swing/AWT** for rendering and UI.
Supports **both keyboard and gamepad/joystick input** via **JInput** (controller mapping is integrated into the gameplay flow).

> **Note:** Some in-game UI texts/dialogues may currently be in Romanian. The gameplay remains clear, and localization can be added later.

---

## Features

- **Input abstraction**: play using **keyboard** or **gamepad/joystick**
  - Controller detection + button/axis mapping (JInput)
  - Seamless fallback to keyboard controls
- **2D tile engine** (map rendering + collisions)
- **Game loop** with FPS control and state handling (menu, gameplay, pause, etc.)
- **Player movement & combat**
- **NPC dialogue / interactions**
- **Enemies (monsters)** and item system
- **Keyboard input** + **gamepad support** (via JInput)
- **SQLite persistence**
  - `highscores.db` (leaderboard)
  - `configuratii.db` (settings / checkpoint-like data)

---

## Tech Stack

- **Java** (Swing/AWT)
- **SQLite** via **JDBC**
- **JInput** (controller/gamepad support)

---

## How to Run (recommended: IntelliJ IDEA)

1. Install a JDK (Java **17** or **11** recommended).
2. Open the project in **IntelliJ IDEA**.
3. Make sure the external libraries are available on the classpath:
   - `sqlite-jdbc` (SQLite driver)
   - `jinput` (only if you want gamepad support)
4. Run the entry point:

   **`src/main/Main.java`**

> If your project also contains a `res/` folder (images/sprites), ensure it is marked as a **Resources Root** in IntelliJ so the game can load textures correctly.

---

## Controls (Keyboard)
## Gamepad Support
The game can be fully played with a controller. Input is handled through JInput and mapped to the same in-game actions as the keyboard (movement, confirm/interact, pause, action).

These are the default mappings found in the code:

- **Move:** `W A S D`
- **Confirm / interact:** `ENTER`
- **Pause:** `P`
- **Back / exit menus:** `ESC`
- **Shoot / action key:** `F`

> If you later change keybinds, update this section accordingly.

---

## Save / Database Files

The game creates/uses local database files (in the project/run directory):

- `highscores.db` — stores the high score table
- `configuratii.db` — stores configuration / progress values

**Recommendation:** keep these files **local** and ignore them in Git (they are runtime artifacts).

Add to `.gitignore`:

```gitignore
# Runtime DB files
*.db
*.sqlite
*.sqlite3
