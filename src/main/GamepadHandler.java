package main;

import net.java.games.input.Component.Identifier;
import net.java.games.input.*;

public class GamepadHandler implements Runnable, InputHandlerInterface {
    private GamePanel gp;
    private Controller gamepad; // controller-ul detectat
    private boolean running = true; // flag pentru a opri thread-ul
    private final float deadzone = 0.2f; // miscare fals pozitiva
    // detectata la joystick, desi nu atingem controllerul
    private float lastStickDir = Component.POV.OFF; // retine ultima directie
    private boolean selectWasPressed = false; // evita spam-ul de selectie
    // intre 2 proiectile
    private short lastSelection = 1;

    // stari ale butoanelor si stick-urilor actualizate in pollGamepad()
    public boolean upPressed, downPressed, rightPressed, leftPressed;
    public boolean aPressed, bPressed, xPressed, yPressed;
    public boolean startPressed, selectPressed, leftThumbPressed, rightThumbPressed;
    public float leftStickX, leftStickY;
    public float rightStickX, rightStickY;
    public float leftTrigger, rightTrigger;

    public GamepadHandler(GamePanel gp) {
        this.gp = gp;
        initGamepad();
        new Thread(this).start(); // ruleaza pe un thread separat
    }

    private void initGamepad() {
        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
        // obtine toate controllerele conectate la sistem
        for (Controller controller : controllers) {
            if (controller.getType() == Controller.Type.GAMEPAD || controller.getType() == Controller.Type.STICK) {
            // gaseste primul controller de tip GAMEPAD/STICK si il salveaza
                gamepad = controller;
                System.out.println("Gamepad found: " + gamepad.getName());
                break;
            }
        }
        if (gamepad == null) {
            System.out.println("No gamepad found!");
        }
    }

    public void pollGamepad() {
        if (gamepad == null) return;

        gamepad.poll();// adaugam in coada de evenimente ce butoane s-au apasat
        EventQueue queue = gamepad.getEventQueue();
        // le aducem in functie cu acest getter
        Event event = new Event(); // un singur input detectat

        while (queue.getNextEvent(event)) { // itereaza prin
            // toate input-urile aparutte de la ultimul poll
            Component comp = event.getComponent();
            float value = event.getValue();

            if (!comp.isAnalog() || value > deadzone) {
                InputHandler.lastDevice = 1; // ultimul s-a folosit ganepad-ul
                // Afiseaza evenimentul pentru debug
                // System.out.println(event);
            }
            // Gestionarea butoanelor
            if (comp.isAnalog()) { // trimite inputul fie la analog(stick/trigger)
                // fie la digital (butoane)
                handleAnalogInput(comp, value);
            } else {
                handleDigitalInput(comp, value);
            }
        }
    }

    private void handleAnalogInput(Component comp, float value) {
        Identifier id = comp.getIdentifier();
        // miscarea este mai mica decat deadzone, ignoram
        if (Math.abs(value) < deadzone) value = 0;
        // salvam valoarea fiecarui stick sau trigger
        if (id == Identifier.Axis.X) leftStickX = value;
        else if (id == Identifier.Axis.Y) leftStickY = value;
        else if (id == Identifier.Axis.RX) rightStickX = value;
        else if (id == Identifier.Axis.RY) rightStickY = value;
        else if (id == Identifier.Axis.Z) leftTrigger = value;
        else if (id == Identifier.Axis.RZ) rightTrigger = value;

        // procesam doar conversia digitala pentru stick-ul stang
        if (id == Identifier.Axis.X || id == Identifier.Axis.Y) {
            updateDigitalStates();
        }
    }

    private void updateDigitalStates() {
        float stickDir;
        // daca stick-ul nu e miscat suficient -> directie neutra
        if (Math.sqrt(leftStickX * leftStickX + leftStickY * leftStickY) < deadzone)
            stickDir = Component.POV.OFF;

        else if (Math.abs(leftStickX) > Math.abs(leftStickY))
            stickDir = (leftStickX < 0 ? Component.POV.LEFT : Component.POV.RIGHT);
        else
            stickDir = (leftStickY < 0 ? Component.POV.UP : Component.POV.DOWN);
        // decide directia in functie de ce axa are valoare mai mare
        if (stickDir != lastStickDir) {
            upPressed = downPressed = leftPressed = rightPressed = false;
            if (stickDir == Component.POV.UP) upPressed = true;
            else if (stickDir == Component.POV.DOWN) downPressed = true;
            else if (stickDir == Component.POV.LEFT) leftPressed = true;
            else if (stickDir == Component.POV.RIGHT) rightPressed = true;
        }
        // seteaza doar o singura directie activa
        // nu repeta apasarea daca stick-ul e mentinut -> doar cand se schimba
        lastStickDir = stickDir;
    }

    private void handleDigitalInput(Component comp, float value) {
        // Aceasta metoda privata primeste 2 lucri:
        // comp = comp. fizica de pe controller care a generat
        // evenimentul
        // value = valoarea starii acelui buton: 1.0f inseamna
        // ca butonul a fost apasat, 0.0f inseamna ca buton eliberat
        Identifier id = comp.getIdentifier();
        boolean pressed = value == 1.0f; // butonul e apasat

        if (id == Identifier.Axis.POV) { // obtinem un identificator
            // unic pentru componenta respectiva
            if (value == Component.POV.UP) upPressed = true;
            else if (value == Component.POV.DOWN) downPressed = true;
            else if (value == Component.POV.LEFT) leftPressed = true;
            else if (value == Component.POV.RIGHT) rightPressed = true;
            else if (value == Component.POV.OFF)
                upPressed = downPressed = leftPressed = rightPressed = false;
        }
        else if (id == Identifier.Button.A) aPressed = pressed;
        else if (id == Identifier.Button.B) bPressed = pressed;
        else if (id == Identifier.Button.X) xPressed = pressed;
        else if (id == Identifier.Button.Y) yPressed = pressed;
        else if (id == Identifier.Button.START) startPressed = pressed;
        else if (id == Identifier.Button.SELECT) selectPressed = pressed;
        else if (id == Identifier.Button.LEFT_THUMB) leftThumbPressed = pressed;
        else if (id == Identifier.Button.RIGHT_THUMB) rightThumbPressed = pressed;
    }

    @Override
    public void run() {
        while (running) {
            pollGamepad();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Opreste thread-ul pentru gamepad
    public void stop() {
        running = false;
    }

    // Butonul alterneaza prin cele 2 butoane de select pentru a alterna proiectilul folosit
    private boolean selectionKey(short nr) {
        // metoda primeste ca parametru un numar care
        // identifica selectia dorita(1 sau 2)
        // butonul RIGHT_THUMB este apasat
        // nu am inregistrat aceasta apasare
        if (!rightThumbPressed) selectWasPressed = false;
        if (rightThumbPressed && !selectWasPressed && nr != lastSelection) {
            // selectia actuala este diferita de ultima selectie
            selectWasPressed = true;
            lastSelection = nr;
            return true;
        }
        return false;
    }

    public boolean upPressed() { return upPressed; }
    public boolean downPressed() { return downPressed; }
    public boolean leftPressed() { return leftPressed; }
    public boolean rightPressed() { return rightPressed; }
    public boolean enterPressed() { return aPressed; }
    public boolean escapePressed() { return startPressed; }
    public boolean pauseKeyPressed() { return selectPressed; }
    public boolean shotKeyPressed() { return xPressed; }
    public boolean selectionKey1Pressed() { return selectionKey((short) 1); }
    public boolean selectionKey2Pressed() { return selectionKey((short) 2); }
}
