package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Clasa GamePanel se ocupa de ecranul jocului
public class GamePanel extends JPanel implements Runnable { // are toate atributele clasei JPanel
    private static GamePanel instance; // atribut privat pentru instanta

    /* ceea ce am setat in Main cu acel screenWidth il putem privi ca "rama acestui joc"
    in cadrul clasei GamePanel, definim "continutul" care trebuie sa se aseze pe rama noastra delimitata de
    dimensiunile ecranului pc-ului respectiv */

    public final int screenWidth = Main.screenWidth;
    public final int screenHeight = Main.screenHeight;
    public final int tileSize = 100; // dimensiune default a unui caracter, tile-uri, npc-uri
    public final int maxScreenCol = screenWidth / tileSize; // 10
    public final int maxScreenRow = screenHeight / tileSize; // 8

    //WORLD SETTINGS
    public final int maxWorldCol = 55;
    public final int maxWorldRow = 60;
    public final int maxMap = 50;
    public int currentMap = 0;

    // Entitati + proiectile
    ArrayList<Entity> entityList = new ArrayList<>(); // entitatea care are cel mai mic worldY are index-ul 0
    public Entity[][] obj = new Entity[maxMap][60]; // folosim ArrayList pentru ca
    // cu add adaugam rapid, eliminam usor(remove) si avem o iterare flexibila
    // fata de un array fix
    public Entity[][] npc = new Entity[maxMap][60];
    public Entity[][] monster = new Entity[maxMap][60];
    public ArrayList<Entity> projectileList = new ArrayList<>();

    // STAREA JOCULUI
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public final int winState = 5;
    public final int nameInputState = 6;
    public String playerName = "Anonim";


    // SYSTEM
    TileManager tileM = new TileManager(this);
    public InputHandler inputH = new InputHandler(this);
    public int escapeCounter = 0;
    public boolean pauseWasPressed = false, enterWasPressed = false,
            directionWasPressed = false, shotWasPressed = false, debugPressed = false;
    Sound music = new Sound();
    Sound se = new Sound();
    Thread gameThread;
    public Collision cChecker = new Collision(this);

    public Player player = new Player(this, inputH);
    public AssetSetter aSetter = new AssetSetter(this);

    public UI ui = new UI(this);
    public EventHandler evHandler = new EventHandler(this);
    Lighting lighting = new Lighting(this, 500);
    public boolean debugMode = false;

    // FPS
    int FPS = 60;

    private GamePanel() { // constructorul, este privat, deci
        // impiedica crearea de instante multiple
        //din exteriorul clasei
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // seteaza dimensiunea preferata
        // a panoului de joc influentand
        // apelul lui PACK()
        this.setBackground(Color.black); // fundalul panoului negru
        this.setDoubleBuffered(true); // reduce palpairea prin desenarea graficii intr-o memorie tampon
        // inainte de afisarea ei pe ecran
        this.addKeyListener(InputHandler.getKeyListener());
        new Thread(InputHandler.getGamepadThread()).start();
        this.setFocusable(true); // este focusat pe inputul primit de la tastatura
        if (player == null) { // check if player is null
            player = new Player(this, inputH);
        }
        //currentMap = player.level;
    }
    public static GamePanel getInstance(){ // statica pentru a fi
        // apelata fara a avea
        // nevoie de o instanta
        if(instance == null){
            instance = new GamePanel();
        }
        return instance;
    }

    public static GamePanel build(){
        return getInstance();

    }

    public void setupGame()
    {
        gameState = titleState;
        aSetter.setObject();
        aSetter.setMonster();
        playMusic(0);
        aSetter.setNPC();
    }


    public void startGameThread(){
        gameThread = new Thread(this); // instantia un obiect GamePnael
        // permite executia codului in paralel, adica putem rula o bucla
        // independent de restul aplicatiei(UI de exemplu)
        gameThread.start(); // se apeleaza metoda run
    }

    // controleaza numarul de cadre pe secunda si pregateste noua scena(update())
    // pe care o desenaza interfata grafica a jocului
    @Override
    public void run() // defineste loop-ul jocului
    {
        double drawInterval = 1000000000/FPS; // cat dureaza un cadru: aprox = 16.666 ms
        double delta = 0; // timpul acumulat
        long lastTime = System.nanoTime(); // cand a inceput ultimul frame
        // a rulat ultima data
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread  != null)
        {
            currentTime = System.nanoTime(); // timpul curent in ns
            delta += (currentTime - lastTime) / drawInterval; // actualizeaza delta cu timpul scurs de la ultimul frame
            timer += (currentTime - lastTime); // adauga timpul scurs la timer
            lastTime = currentTime; // actualizeaza lastTime la timpul curent
            if(delta >= 1) // a trecut suficient timp pentru un nou frame
            {
                update(); // actualizam starea jocului(pozitii, creaza diferite tile-uri, npc, etc.)
                repaint(); // functie din clasa JComponent
                // solicita sistemul de fereastra sa redeseneze componenta grafica
                // Genereaza un eveniment intern de redesanre, gestionat asincron
                // de sistemul grafic EDT - Event Dispatch Thread
                // la momentul oportun repaint trimite o cerere sa redeseneze GamePanel
                // Java apeleaza automat paintComponent
                // separa logica update de randare -> Paint Component
                delta--;
                drawCount ++;
            }
//            if(timer >= 1000000000){ // debug FPS-uri
//                System.out.println("FPS: " + drawCount);
//                drawCount = 0;
//                timer = 0;
//            }
        }
    }

    public void update() {
        // TITLE STATE
        if (gameState == titleState) {
            // directionWasPressed este un flag care memoreaza daca
            // tastele de directie(sus/jos) au fost apasate in update-ul
            // anterior, astfel cand utilizatorul tine apasata o tasta
            // sus sau jos, modificarea lui ui.commandNum se face o singura data
            if ((inputH.upPressed() || inputH.downPressed()) && !directionWasPressed) {
                directionWasPressed = true;
                if (inputH.upPressed()) ui.commandNum = (ui.commandNum + 2) % 3;
                if (inputH.downPressed()) ui.commandNum = (ui.commandNum + 1) % 3;
            }
            if (!(inputH.upPressed() || inputH.downPressed())) directionWasPressed = false;
            if (inputH.enterPressed() && !enterWasPressed) {
                enterWasPressed = true;
                if (ui.commandNum == 0) {
                    gameState = nameInputState;
                    ui.enteringName = true;
                    ui.typedName = "";
                } else if (ui.commandNum == 1) {
                    gameState = playState;
                    load();
                    stopMusic();
                    playMusic(1);
//                    System.out.println("load");
                } else if (ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        else if (gameState == nameInputState) {
            if (inputH.enterPressed() && !enterWasPressed) {
                enterWasPressed = true;
                if (ui.typedName.trim().isEmpty()) {
                    playerName = "Anonymous";
                } else {
                    playerName = ui.typedName.trim();
                }
                player.db.updateName(playerName);
                ui.enteringName = false;
                gameState = playState;
                newgame();
                stopMusic();
                playMusic(1);
            }
        }
        // PLAY STATE
        if(gameState == playState) {
            if(inputH.escapePressed()) {
                escapeCounter++;
                if (escapeCounter == 30) {
                    System.exit(0);
                }
            }
            else if (escapeCounter > 0) {
                escapeCounter -= 3;
            }
            if (inputH.pauseKeyPressed()) pauseWasPressed = true;
            if (!inputH.pauseKeyPressed() && pauseWasPressed) {
                pauseWasPressed = false;
                gameState = pauseState;
            }
            if (inputH.debugPressed()) debugPressed = true;
            if (!inputH.debugPressed() && debugPressed) {
                debugPressed = false;
                debugMode = !debugMode;
            }
            player.update();
            // MONSTER
            for(int i = 0; i < monster[1].length; i ++)
            {
                if(monster[currentMap][i] != null)
                {
                    if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false){
                        monster[currentMap][i].update();
                    }
                    if(monster[currentMap][i].alive == false){
                        monster[currentMap][i] = null;
                    }
                }
            }
            // NPC
            for(int i = 0; i < npc[1].length; i ++) {
                if(npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            // PROJECTILES
            for(int i = 0; i < projectileList.size(); i ++) {
                if(projectileList.get(i) != null) {
                    if(projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    }
                    if(!projectileList.get(i).alive) {
                        projectileList.remove(i);
                    }
                }
            }
        }
        // PAUSE STATE
        else if(gameState == pauseState){
            if (inputH.pauseKeyPressed()) pauseWasPressed = true;
            if (!inputH.pauseKeyPressed() && pauseWasPressed) {
                pauseWasPressed = false;
                gameState = playState;
            }
        }
        // DIALOGUE STATE
        else if(gameState == dialogueState) {
            if(inputH.enterPressed() && !enterWasPressed) {
                enterWasPressed = true;
                gameState = playState;
            }
        }
        // GAMEOVER STATE
        else if(gameState == gameOverState) {
            stopMusic();
            if(inputH.upPressed()) ui.commandNum = 0;
            if(inputH.downPressed()) ui.commandNum = 1;

            if(inputH.enterPressed()){
                if(ui.commandNum == 0){
                    retry();
                    gameState = playState;
                    playMusic(1);
                }
                else if(ui.commandNum == 1){
                    System.exit(0);
                }
            }
        }
        // WIN STATE
        else if(gameState == winState) {
            if(inputH.escapePressed()) {
                escapeCounter++;
                if (escapeCounter == 30) {
                    System.exit(0);
                }
            }
            else if (escapeCounter > 0) {
                escapeCounter -= 3;
            }
        }
        inputH.update();
        if(!inputH.enterPressed()) enterWasPressed = false;
    }

    public void retry(){
        boolean previousSessionToy = player.hasToy;
        player.setDefaultValues(false);
        // daca ne aflam in level 2 sau 3, trebuie sa tinem
        // minte explicit ca Tom avea calitatea de a arunca
        // cu jucarii de la nivelul 1
        if(player.hasCollectedBook){
            player.hasCollectedBook = false;
        }
        if(previousSessionToy && currentMap > 0) player.hasToy = true;
        tileM.loadMap("/maps/world01.txt",0);
        tileM.loadMap("/maps/world02.txt",1);
        tileM.loadMap("/maps/world03.txt",2);
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();
        if(currentMap==1) teleport(1, 4, 7);
        else if(currentMap==2)
        {
            teleport(2,7,3);
        }
    }

    public void teleport(int map, int x, int y) {
        currentMap=map;
        player.worldX=x*tileSize;
        player.worldY=y*tileSize;
    }

    public void load() {
        player.setDefaultValues(true);
        playerName = player.db.GetName();
        tileM.loadMap("/maps/world01.txt",0);
        tileM.loadMap("/maps/world02.txt",1);
        tileM.loadMap("/maps/world03.txt",2);
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();
    }

    public void newgame() {
        player.setDefaultValues(false);
        tileM.loadMap("/maps/world01.txt",0);
        tileM.loadMap("/maps/world02.txt",1);
        tileM.loadMap("/maps/world03.txt",2);
        aSetter.setNPC();
        aSetter.setMonster();
        aSetter.setObject();
    }

    public void paintComponent(Graphics g) { // Graphics - clasa care are multe functii de a desena obiecte pe ecran
        // paintCompoment este o metoda build-in a limbajului Java
        super.paintComponent(g); // clasa parinte, JPanel
        Graphics2D g2 = (Graphics2D) g; // Graphics2D furnizeaza control mai sofisticat cand vine vb de geometrie
        // transformari de coordonate, culoare si text layout

        // TITLE SCREEN
        if(gameState == titleState) {
            ui.draw(g2);
        }
        else {
            tileM.draw(g2);
            player.draw(g2);
            entityList.add(player);
            for(int i = 0; i < obj[1].length; i ++)
            {
                if(obj[currentMap][i] != null)
                {
                    entityList.add(obj[currentMap][i]);
                }
            }
            for(int i = 0; i < monster[1].length; i ++)
            {
                if(monster[currentMap][i] != null)
                {
                    entityList.add(monster[currentMap][i]);
                }
            }
            for(int i = 0; i < npc[1].length; i ++)
            {
                if(npc[currentMap][i] != null)
                {
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i = 0; i < projectileList.size(); i ++)
            {
                if(projectileList.get(i) != null)
                {
                    entityList.add(projectileList.get(i));
                }
            }
            // DRAW ENTITIES
            for(int i = 0; i < entityList.size(); i ++)
            {
                entityList.get(i).draw(g2);
            }
            // EMPTY ENTITY LIST
            entityList.clear();
            // LIGHTING
            if (currentMap == 1) lighting.draw(g2);
            // UI
            ui.draw(g2);
        }
        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();

    }
    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){ // sound effect
        se.setFile(i);
        se.play();
    }
}


