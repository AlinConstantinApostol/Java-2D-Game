package main;

import java.awt.event.KeyListener;

public class InputHandler implements InputHandlerInterface{
    public static short lastDevice = 0; // 0 = keyboard, 1 = gamepad
    private static InputHandlerInterface keyHandler;
    private static InputHandlerInterface gamepadHandler;
    private InputHandlerInterface currentHandler;

    public InputHandler(GamePanel gp) {
        keyHandler = new KeyHandler(gp);
        gamepadHandler = new GamepadHandler(gp);
        //GamepadHandler.inputHandler = this; // dam acces la inputHandler static
        currentHandler = keyHandler; // default
    }

    public void update() {
        if (lastDevice == 0) {
            currentHandler = keyHandler;
        } else {
            currentHandler = gamepadHandler;
        }
    }

    public static KeyListener getKeyListener() {
        return (KeyListener) keyHandler;
    }
    public static Runnable getGamepadThread() {
        return (Runnable) gamepadHandler; // GamePadHandler implementeaza Runnable
        // pentru a putea fi rulata pe un thread separat
    }
    public static KeyHandler getKeyHandler() { return (KeyHandler) keyHandler; }

    // Aceste metode vor fi apelate din GamePanel sau Player
    public boolean upPressed() { return currentHandler.upPressed(); }
    public boolean downPressed() { return currentHandler.downPressed(); }
    public boolean leftPressed() { return currentHandler.leftPressed(); }
    public boolean rightPressed() { return currentHandler.rightPressed(); }
    public boolean enterPressed() { return currentHandler.enterPressed(); }
    public boolean escapePressed() { return currentHandler.escapePressed(); }
    public boolean pauseKeyPressed() { return currentHandler.pauseKeyPressed(); }
    public boolean shotKeyPressed() { return currentHandler.shotKeyPressed(); }
    public boolean selectionKey1Pressed() { return currentHandler.selectionKey1Pressed(); }
    public boolean selectionKey2Pressed() { return currentHandler.selectionKey2Pressed(); }

    public boolean debugPressed() { return getKeyHandler().debugPressed(); }
}
