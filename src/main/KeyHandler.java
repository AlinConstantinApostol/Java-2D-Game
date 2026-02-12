package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener, InputHandlerInterface {
    // aceasta metoda permite clasei GamePanel sa aiba acces la keyHandler
    // ca un KeyListener pentru a-l inregistra al fereastra grafica
    GamePanel gp;
    public boolean upPressed, downPressed, rightPressed, leftPressed, enterPressed, escapePressed, pauseKeyPressed,
            shotKeyPressed, selectionKey1Pressed, selectionKey2Pressed, altPressed, cPressed;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }


    // Se apeleaza cand o tasta este apasata
    @Override
    public void keyPressed(KeyEvent e) {
        InputHandler.lastDevice = 0; // ultimul s-a folosit keyboard-ul
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) upPressed = true;
        if (code == KeyEvent.VK_S) downPressed = true;
        if (code == KeyEvent.VK_A) leftPressed = true;
        if (code == KeyEvent.VK_D) rightPressed = true;
        if (code == KeyEvent.VK_P) pauseKeyPressed = true;
        if (code == KeyEvent.VK_ESCAPE) escapePressed = true;
        if(code == KeyEvent.VK_ENTER) enterPressed = true;
        if (code == KeyEvent.VK_F) shotKeyPressed = true;
        if (code == KeyEvent.VK_1) selectionKey1Pressed = true;
        if (code == KeyEvent.VK_2) selectionKey2Pressed = true;

        if (code == KeyEvent.VK_ALT) altPressed = true;
        if (code == KeyEvent.VK_C) cPressed = true;
    }


    // Se apeleaza cand o tasta este eliberata
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W) upPressed = false;
        if(code == KeyEvent.VK_S) downPressed = false;
        if(code == KeyEvent.VK_A) leftPressed = false;
        if(code == KeyEvent.VK_D) rightPressed = false;
        if(code == KeyEvent.VK_P) pauseKeyPressed = false;
        if(code == KeyEvent.VK_F) shotKeyPressed = false;
        if(code == KeyEvent.VK_ESCAPE) escapePressed = false;
        if(code == KeyEvent.VK_ENTER) enterPressed = false;
        if(code == KeyEvent.VK_1) selectionKey1Pressed = false;
        if(code == KeyEvent.VK_2) selectionKey2Pressed = false;

        if (code == KeyEvent.VK_ALT) altPressed = false;
        if (code == KeyEvent.VK_C) cPressed = false;
    }

    public void keyTyped(KeyEvent e) {
        if (gp.gameState == gp.nameInputState && gp.ui.enteringName) {
            char c = e.getKeyChar();
            if (Character.isLetterOrDigit(c) || Character.isWhitespace(c)) {
                gp.ui.typedName += c;
            }
            if (c == '\b' && gp.ui.typedName.length() > 0) {
                gp.ui.typedName = gp.ui.typedName.substring(0, gp.ui.typedName.length() - 1);
            }
        }
    }

    public boolean upPressed() { return upPressed; }
    public boolean downPressed() { return downPressed; }
    public boolean leftPressed() { return leftPressed; }
    public boolean rightPressed() { return rightPressed; }
    public boolean enterPressed() { return enterPressed; }
    public boolean escapePressed() { return escapePressed; }
    public boolean pauseKeyPressed() { return pauseKeyPressed; }
    public boolean shotKeyPressed() { return shotKeyPressed; }
    public boolean selectionKey1Pressed() { return selectionKey1Pressed; }
    public boolean selectionKey2Pressed() { return selectionKey2Pressed; }

    public boolean debugPressed() { return altPressed && cPressed; }
}