package main;

import entity.Entity;
import entity.Player;
import object.Heart;
import object.Key;
import object.Manna;
import object.Toy;
import object.Book;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UI {
    GamePanel gp;

    Graphics2D g2;
    Font arial_40, arial_80B;
    BufferedImage keyImage, toyImage, bookImage;
    public boolean messageOn = false;
    BufferedImage heart_full, heart_blank, heart_half, manna_full, manna_blank;

    double playTime;
    int messageCounter = 0;
    public String message = "";
    public String currentDialogue = "";
    public int commandNum = 0;
    public boolean enteringName = false;
    public String typedName = "";
    boolean scoreSaved = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Cambria", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

        // Initializarea elementelor HUD si a imaginilor acestora
        Entity heart = new Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity manna = new Manna(gp);
        manna_blank = manna.image;
        manna_full = manna.image2;
        Key key = new Key(gp);
        keyImage = key.down1;
        Toy toy = new Toy(gp);
        toyImage = toy.up1;
        Book book = new Book(gp);
        bookImage = book.up1;
    }

    // Afisarea de mesaje jucatorului
    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    // Desenarea elementelor UI pe ecran
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);

        // Deseneaza cheia
        if(gp.player.hasKey) {
            g2.drawImage(keyImage, gp.screenWidth-gp.tileSize*2+50,  gp.screenHeight-gp.tileSize-10, gp.tileSize, gp.tileSize, null);
        }

        // Deseneaza proiectilul activ
        if (Player.hasToy || Player.hasBook) {
            switch (Player.activeWeapon) {
                case 1: g2.drawImage(toyImage, gp.screenWidth-gp.tileSize*3+50,  gp.screenHeight-gp.tileSize-10, gp.tileSize, gp.tileSize, null); break;
                case 2: g2.drawImage(bookImage, gp.screenWidth-gp.tileSize*3+50,  gp.screenHeight-gp.tileSize-10, gp.tileSize, gp.tileSize, null); break;
            }
        }

        // Deseneaza mesajele pentru jucator
        if(messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize*7);
            messageCounter++;

            if (messageCounter > 180) {
                messageCounter = 0;
                messageOn = false;
            }
        }

        if (gp.gameState == gp.nameInputState) {
            drawNameInputScreen();
        }

        // TITLE STATE
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        // PLAY STATE
        if (gp.gameState == gp.playState) {
            drawPlayerLife();
        }

        // PAUSE STATE
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        if (gp.escapeCounter > 0) {
            drawQuit();
        }

        // DIALOGUE STATE
        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        // GAME OVER STATE
        if(gp.gameState == gp.gameOverState) {
            drawGameOverScreen();
        }

        // WIN STATE
        if(gp.gameState == gp.winState) {
            drawWinScreen();
        }
    }

    public void drawWinScreen() {
        //g2.fillRect(0, 0, gp.screenWidth / 3, gp.screenHeight / 3);

        int x;
        int y;
        String text, scoreText;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 30f));

        text = "You won! You kicked Jerry out of the house for good!";
        scoreText = "Your score: " + gp.player.score;
        // Shadow
        g2.setColor(Color.white);
        x = getXforCenteredText(text);// RESET
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 56f));
        x = getXforCenteredText(scoreText);// RESET
        y = gp.screenHeight * 3/4;
        g2.drawString(scoreText, x, y);
        ScoreManager db = new ScoreManager();
        if (!scoreSaved) {
            db.insertScore(gp.playerName, gp.player.score);
            scoreSaved = true;
        }

        int highScore = db.getHighScore();
        int rank = db.getPlayerRank(gp.playerName, gp.player.score);

        String highScoreText = "High Score: " + highScore;
        String rankText = "You ranked in the: #" + rank;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40f));
        x = getXforCenteredText(highScoreText);
        y += gp.tileSize;
        g2.drawString(highScoreText, x, y);

        x = getXforCenteredText(rankText);
        y += gp.tileSize;
        g2.drawString(rankText, x, y);


    }

    public void drawPlayerLife() {
        // DRAW PLAYER LIFE
        int x = gp.tileSize / 3 - 10;
        int y = gp.tileSize / 2 - 20;

        for (int i = 0; i < gp.player.maxLife; ++i) {
            if (i < gp.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            else {
                g2.drawImage(heart_blank, x, y, null);
            }
            x += gp.tileSize - 20;
        }

        // DRAW PLAYER manna
        x = gp.tileSize / 6 - 12;
        y = gp.tileSize;
        for(int i = 0; i < gp.player.maxManna; ++i) {
            if (i < gp.player.manna) {
                g2.drawImage(manna_full, x, y, null);
            }
            else {
                g2.drawImage(manna_blank, x, y, null);
            }
            x += 70;
        }

        int aux_x = gp.screenWidth-gp.tileSize*2;
        int aux_y = gp.tileSize - 20;

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,40F));
        g2.drawString("Scor: " + gp.player.score, aux_x,aux_y);
        // Test variable
        // g2.drawString("canTouch: " + gp.player.canTouchEvent, 40, gp.screenHeight - 40);
    }

    public void drawTitleScreen() {
        BufferedImage fundal = null;
        try {
            fundal = ImageIO.read(getClass().getResource("/fundal/fundal.png"));
        }
        catch (IOException e) {
            System.out.println("Failed to load image!");
        }

        g2.drawImage(fundal, 0, 0, gp.screenWidth, gp.screenHeight, null);
        if(gp.gameState == gp.titleState) {

            // TITLE NAME
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "Tom's Revenge";
            int x = getXforCenteredText(text);
            int y = gp.tileSize;

            //SHADOW
            g2.setColor(Color.gray);
            g2.drawString(text, x + 5, y + 5);
            //MAIN COLOR
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            g2.drawImage(gp.player.cover, x + 620, 0, 196, 70, null);


            // TOM IMAGE

            // MENU
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

            text = "NEW GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);

            if(commandNum == 0) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "LOAD GAME";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 1) {
                g2.drawString(">", x-gp.tileSize, y);
            }

            text = "QUIT";
            x = getXforCenteredText(text);
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if(commandNum == 2) {
                g2.drawString(">", x-gp.tileSize, y);
            }
        }
    }

    public void drawNameInputScreen() {
        drawTitleScreen(); // desenează fundalul

        int boxWidth = 500;
        int boxHeight = 150;
        int x = gp.screenWidth / 2 - boxWidth / 2;
        int y = gp.screenHeight / 2 - boxHeight / 2;

        drawSubWindow(x, y, boxWidth, boxHeight);

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        g2.drawString("Introdu numele:", x + 40, y + 50);

        g2.setFont(arial_80B);
        g2.drawString(typedName + "_", x + 40, y + 110);
    }

    public void drawPauseScreen() {
        g2.setColor(new Color(0, 0, 0, 127));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setColor(new Color(255, 255, 255, 255));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PE PAUZĂ";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawQuit() {
        int gradient = Math.min(255, gp.escapeCounter * 9);
        g2.setColor(new Color(0, 0, 0, gradient));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);
        g2.setColor(new Color(255, 255, 255, gradient));
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
        String text = "QUITTING", dots = "";
        if (gp.escapeCounter > 25) {
            dots = "...";
        }
        else if (gp.escapeCounter > 15) {
            dots = "..";
        }
        else if (gp.escapeCounter > 5) {
            dots = ".";
        }
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text + dots, x, y);
    }

    public void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));

        text = "Game Over";
        // Shadow
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize * 4;
        g2.drawString(text, x, y);

        // Main
        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        // Retry
        g2.setFont(g2.getFont().deriveFont(50f));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*2;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        // Back to the Title Screen
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x - 40, y);
        }
    }

    public void drawDialogueScreen(){
        // WINDOW
        int x = 40;
        int y = gp.screenHeight - gp.tileSize * 2 - 30;
        int width = gp.screenWidth - (gp.tileSize * 5);
        int height = gp.tileSize * 2;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
        x += gp.tileSize;
        y += gp.tileSize;
        for(String line : currentDialogue.split("\n")) { // face split la text in momentul in care gaseste \n
            g2.drawString(line, x, y);
            y += 40; // se trece in urmatorul rand
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200); // seteaza transparenta al 4-lea argument
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5)); // defineste latimea marginilor care sunt
        // randate cu graphics2D
        g2.drawRoundRect(x+5, y+5, width-10, height-19, 25, 25);
    }

    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
    }
}