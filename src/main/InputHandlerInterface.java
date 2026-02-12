package main;

// Interface: sablon care defineste ce metode trebuie
// sa aiba clasele care se ocupa cu input-ul
public interface InputHandlerInterface {
    boolean upPressed();
    boolean downPressed();
    boolean leftPressed();
    boolean rightPressed();
    boolean enterPressed();
    boolean escapePressed();
    boolean pauseKeyPressed();
    boolean shotKeyPressed();
    boolean selectionKey1Pressed();
    boolean selectionKey2Pressed();
}

