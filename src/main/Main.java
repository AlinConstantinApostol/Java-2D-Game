package main;

import javax.swing.JFrame; //folosit creare fereastra grafica
import java.awt.*; // furnizeaza un mod prin care putem construi aplicatii grafice
//Include clase grafice precum Graphics, Dimension, GraphicsDevice

// independente de platforma
public class Main {
    public static boolean fullscreen = true; // seteaza daca jocul va rula in ecran
    // complet sau nu
    public static int screenWidth; // dimensiunea ferestrelor calculate la runtime
    public static int screenHeight;

    public static void main(String[] args) {
        GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
        //  se obtine mediul grafic si dispozitivul principal de afisare
        GraphicsDevice device = graphics.getDefaultScreenDevice(); // obtine monitorul principal
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
       // Toolkit.getDefaultToolkit() -> clasa de utilitati care da acces
        // la resursele sistemului
        // returneaza rezolutia completa a ecranului
        screenWidth = (int) screenSize.getWidth(); // extrase din Dimension si
        screenHeight = (int) screenSize.getHeight(); // salvate in var globale
        JFrame window = new JFrame(); // creaza fereastra standard Swing jocului
        window.setTitle("Tom's Revenge"); // seteaza titlu in bara de titlu
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // aplicatia se termina complet la iesire

        GamePanel gamePanel = GamePanel.build(); // creaza un nou obiect 'GamePanel', care este panelul
        // unde se va juca jocul, returnam singleton, adica exista o singura
        // instanta GamePanel in joc
        // Metode din Java Swing
        window.add(gamePanel); // adaugam panoul de joc in fereastra => GamePanel va fi
        // ceea ce utilizatorul vede in fereastra principala
        window.setUndecorated(false); // dorim bara de titlu, butoane de inchidere
        window.pack(); // ajusteaza dimensiunea ferestrei pentru a se
        // potrivi perfect cu dimensiunea preferata a componentelor
        // setPrefferedSize() se ocupa de asta in GamePanel
        if (fullscreen) {
            device.setFullScreenWindow(window);
        } // fullscreen = true ==> fereastra devine fullscreen
        else {
            window.setLocationRelativeTo(null); // centreaza fereastra pe ecran
            window.setResizable(true); // dimensiunea ferestrei este modificabila
        }
        window.setVisible(true); // face fereastra vizibila
        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}