package entity;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Entity {
    GamePanel gp;
    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5,
            right1, right2, right3, right4, right5, left1, left2, left3, left4, left5, cover, slide,
            screamUp, screamDown, screamLeft, screamRight;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackLeft3,
            attackRight1, attackRight2, attackRight3;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0, 0, 50, 50);
    public int solidAreaDefaultX = 25, solidAreaDefaultY = 25;
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
    public boolean collision = false;
    String[] dialogues = new String[40];

    // STATE
    public int worldX, worldY; // coordonatele entitatii pe harta
    public String direction = "down";
    public int spriteNum = 1;
    public boolean collisionOn = false;
    public boolean invincible = false;
    public boolean alive = true;
    public boolean dying = false;
    boolean attacking = false;
    public boolean hpBarOn = false;
    public boolean isChasingPlayer = false;
    public boolean touchSoap = true;
    public boolean touchPioneze = true;
    int dialogueIndex = 0;

    // COUNTER
    public int spriteCounter = 0;
    public int shotAvailableCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter2 = 0;
    public int directionLockCounter = 0;
    int dyingCounter = 0;
    int hpBarCounter = 0;

    // CHARACTER ATTRIBUTES
    public int speed;
    public int maxManna;
    public int manna;
    public int ammo;
    public String name;
    public int maxLife;
    public int life;
    public int attack = 1;
    public int projectileType = 0;
    public Projectile projectile;
    public Projectile projectile2;

    // ITEM ATRIBUTES
    public int useCost;

    // TYPE
    public int type; // 0 = plater, 1 = npc, 2 = monster

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public abstract void getImage();
    public void setAction() {}

    public void damageReaction(){}


    // Efectuam modificari asupra diferitelor entitati
    public void update() {
        setAction();
        if(directionLockCounter > 0) {
            directionLockCounter--;
        }

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        if(this.type == 2 && contactPlayer) // clasa este monstru si jucatorul a fost atins
        {
            damagePlayer(attack);
        }

        if(!collisionOn) {
            switch(direction){
                case "up": {
                    worldY -= speed;
                    break;
                }
                case "down": {
                    worldY += speed;
                    break;
                }
                case "left": {
                    worldX -= speed;
                    break;
                }
                case "right": {
                    worldX += speed;
                    break;
                }
            }
        }

        spriteCounter++;
        if(spriteCounter > 8){ // se schimba sprite-ul odata la fiecare 10 cadre
            if(spriteNum == 1) {
                spriteNum = 2;
            } else if(spriteNum == 2) {
                spriteNum = 3;
            } else if(spriteNum == 3) {
                spriteNum = 1;
            }
            else if(spriteNum == 4) {
                spriteNum = 5;
            } else if(spriteNum == 5) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
        if(invincible)
        {
            invincibleCounter++;
            if(invincibleCounter > 40){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(shotAvailableCounter < 30){
            shotAvailableCounter ++;
        }
        if(isChasingPlayer){
            chasePlayer();
            // Oprire urmarire dupa o anumita distanța
            int distanceToPlayer = Math.abs(gp.player.worldX - this.worldX) + Math.abs(gp.player.worldY - this.worldY);
            if (distanceToPlayer > 700) {
                isChasingPlayer = false;
            }
        }

    }


    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        // Only draw the entity if it is within the visible screen
        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY < gp.player.worldY + gp.player.screenY + gp.tileSize) {
            switch (direction) {
                case "up":
                    if (spriteNum == 1) image = up1;
                    else if (spriteNum == 2) image = up2;
                    else if (spriteNum == 3) image = up3;
                    else if (spriteNum == 4) image = up4;
                    else if (spriteNum == 5) image = up5;
                    break;
                case "down":
                    if (spriteNum == 1) image = down1;
                    else if (spriteNum == 2) image = down2;
                    else if (spriteNum == 3) image = down3;
                    else if (spriteNum == 4) image = down4;
                    else if (spriteNum == 5) image = down5;
                    break;
                case "right":
                    if (spriteNum == 1) image = right1;
                    else if (spriteNum == 2) image = right2;
                    else if (spriteNum == 3) image = right3;
                    else if (spriteNum == 4) image = right4;
                    else if (spriteNum == 5) image = right5;
                    break;
                case "left":
                    if (spriteNum == 1) image = left1;
                    else if (spriteNum == 2) image = left2;
                    else if (spriteNum == 3) image = left3;
                    else if (spriteNum == 4) image = left4;
                    else if (spriteNum == 5) image = left5;
                    break;
                default:
                    break;
            }
            // Monster HP bar
            if(type == 2 && hpBarOn) {

                double oneScale= (double)gp.tileSize/maxLife; // daca lungimea health bar este de 50, iar
                double hpBarValue = oneScale*life;            // maxLife = 2, atunci fiecare punct de viata
                g2.setColor(new Color(35, 35, 35));  // va avea 25 de pixeli lungime
                g2.fillRect(screenX - 1, screenY - 16, gp.tileSize + 2, 12);

                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 15, (int)hpBarValue, 10);

                hpBarCounter ++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    hpBarOn = false;
                }
            }
            if(invincible){
                hpBarOn = true;
                hpBarCounter = 0;
                changeAlpha(g2, 0.4F);
            }
            if (image != null) {
                if (name == "Dog") {
                    g2.drawImage(image, screenX, screenY, 145, 145, null);
                } else {
                    g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
                }
                // deseneaza solid area al obiectului (pentru testare)
                if (gp.debugMode) {
                    g2.setColor(Color.blue);
                    g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
                }
            }

            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void changeAlpha(Graphics2D g2, float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
    }
    public BufferedImage setupJ(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image, 70, 70);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }

    // Urmareste jucatorul
    public void chasePlayer() {
        if (actionLockCounter <= 0) {
            // Direcția către jucător
            int dx = gp.player.worldX - this.worldX;
            int dy = gp.player.worldY - this.worldY;
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    direction = "right";
                } else {
                    direction = "left";
                }
            }
            else {
                if (dy > 0) {
                    direction = "down";
                } else {
                    direction = "up";
                }
                moveInDirection(direction);
            }
            directionLockCounter = 1;
        }
    }

    public void damagePlayer(int attack) {
        if (!gp.player.invincible) {
            if(gp.player.life > 1) gp.playSE(5);
            gp.player.life -= attack;
            gp.player.invincible = true;
            gp.player.boostTimer = Math.max(gp.player.boostTimer - 200, 0);
        }
    }

    public void moveInDirection(String direction) {
        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }
    }

    public void speak() {
        switch(gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}