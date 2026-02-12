package entity;

import main.GamePanel;
import main.InputHandler;
import main.SQL;
import main.ScoreManager;
import object.Toy;
import object.Book;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    // Pozitia jucatorului in mijlocul ecranului (final pentru ca nu se schimba)
    public final int screenX;
    public final int screenY;

    public SQL db;
    public ScoreManager db2;
    InputHandler input;
    Projectile currentProj;

    public int level;
    public int score = 0;
    public boolean hasKey = false;
    public boolean hasTorch = false;
    public boolean hasTalkedToButch = false;
    public int slideCounter = 0;
    public int pionezeCounter = 0;
    public int attackCounter = 0;

    public static boolean hasToy, hasBook;
    public boolean hasCollectedBook;
    public static final int TOY = 1;
    public static final int BOOK = 2;
    public static int activeWeapon = 0;
    public int weaponTimer = 0;

    public int boostTimer = 0;

    public boolean canTouchEvent = true;
    int previousEventX, previousEventY;

    public Player(GamePanel gp, InputHandler inputH) {
        super(gp);
        this.input = inputH;
        db = new SQL();
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        //solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        solidArea.x = 20;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 60;
        solidArea.height = 70;

        attackArea.width = 80;
        attackArea.height = 60;

        // Incaraca imaginile jucatorului
        getPlayerImage();
    }

    public void setDefaultValues(boolean dbLoad) {
        int loadedLife;

        // STATUS
        worldX = gp.tileSize * 7;
        worldY = gp.tileSize * 52;

        speed = 8;
        direction = "up";
        projectile = new Toy(gp);
        projectile2 = new Book(gp);
        level = 0;
        score = 0;
        maxLife = 7;
        maxManna = 8;
        life = maxLife;
        manna = 0;
        hasToy = false;
        hasBook = false;
        hasKey = false;
        hasTalkedToButch = false;

        if (dbLoad) {
            loadedLife = db.GetLife();
            if (loadedLife > 0) life = loadedLife; else life = maxLife;
            manna = Math.max(db.GetManna(), 0);
            level = Math.max(db.GetLevel(), 0);
            score = Math.max(db.GetScore(), 0);
            hasKey = db.GetKey();
            hasToy = db.GetToy();
            hasBook = db.GetBook();
            hasTalkedToButch = db.GetHasTalkedToButch();
        }

        if (level == 1) teleport(1, 4, 7);
        if (level == 2) teleport(2, 7, 3);

        if (hasBook) activeWeapon = 2;
        else if (hasToy) activeWeapon = 1;

        db.updateLife(life);
        db.updateManna(manna);
        db.updateLevel(level);
        db.updateScore(score);
        db.updateToy(hasToy);
        db.updateBook(hasBook);
        db.updateKey(hasKey);
        db.updateHasTalkedToButch(hasTalkedToButch);
    }

    public void getPlayerImage() {
        up1 = setup("/Tom/tom_U1", gp.tileSize, gp.tileSize);
        up2 = setup("/Tom/tom_U2", gp.tileSize, gp.tileSize);
        up3 = setup("/Tom/tom_U3", gp.tileSize, gp.tileSize);
        up4 = setup("/Tom/tom_U4", gp.tileSize, gp.tileSize);
        up5 = setup("/Tom/tom_standU", gp.tileSize, gp.tileSize);
        down1 = setup("/Tom/tom_D1", gp.tileSize, gp.tileSize);
        down2 = setup("/Tom/tom_D2", gp.tileSize, gp.tileSize);
        down3 = setup("/Tom/tom_D3", gp.tileSize, gp.tileSize);
        down4 = setup("/Tom/tom_D4", gp.tileSize, gp.tileSize);
        down5 = setup("/Tom/tom_standD", gp.tileSize, gp.tileSize);
        right1 = setup("/Tom/tom_R1", gp.tileSize, gp.tileSize);
        right2 = setup("/Tom/tom_R2", gp.tileSize, gp.tileSize);
        right3 = setup("/Tom/tom_R3", gp.tileSize, gp.tileSize);
        right4 = setup("/Tom/tom_R4", gp.tileSize, gp.tileSize);
        right5 = setup("/Tom/tom_standR", gp.tileSize, gp.tileSize);
        left1 = setup("/Tom/tom_L1", gp.tileSize, gp.tileSize);
        left2 = setup("/Tom/tom_L2", gp.tileSize, gp.tileSize);
        left3 = setup("/Tom/tom_L3", gp.tileSize, gp.tileSize);
        left4 = setup("/Tom/tom_L4", gp.tileSize, gp.tileSize);
        left5 = setup("/Tom/tom_standL", gp.tileSize, gp.tileSize);

        attackUp2 = setup("/Tom/attack_up11", gp.tileSize, gp.tileSize);
        attackUp1 = setup("/Tom/attack_up22", gp.tileSize, gp.tileSize);
        attackDown1 = setup("/Tom/attack_down11", gp.tileSize, gp.tileSize);
        attackDown2 = setup("/Tom/attack_down12", gp.tileSize, gp.tileSize);
        attackLeft1 = setup("/Tom/attack_left1", gp.tileSize, gp.tileSize);
        attackLeft2 = setup("/Tom/attack_left2", gp.tileSize, gp.tileSize);
        attackLeft3 = setup("/Tom/attack_left3", gp.tileSize, gp.tileSize);
        attackRight1 = setup("/Tom/attack_right1", gp.tileSize, gp.tileSize);
        attackRight2 = setup("/Tom/attack_right2", gp.tileSize, gp.tileSize);
        attackRight3 = setup("/Tom/attack_right3", gp.tileSize, gp.tileSize);

        screamUp = setup("/Tom/tom_scream_up1",gp.tileSize, gp.tileSize);
        screamDown = setup("/Tom/tom_scream_down1",gp.tileSize, gp.tileSize);
        screamLeft = setup("/Tom/tom_scream_left1",gp.tileSize, gp.tileSize);
        screamRight = setup("/Tom/tom_scream_right1",gp.tileSize, gp.tileSize);

        slide = setup("/Tom/slide",gp.tileSize, gp.tileSize);

        cover = setup("/Tom/cover", gp.tileSize, gp.tileSize);
    }

    public void update(){ // acutalizeaza pozitia si directia jucatorului, este apelata de 60 de ori pe secunda
        // creste spriteCounter cu fiecare cadru

        if(slideCounter > 0){
            slideCounter--;
            // Dublarea temporara a vitezei la alunecare
            switch (direction) {
                case "up":
                    worldY -= speed * 2;
                    break;
                case "down":
                    worldY += speed * 2;
                    break;
                case "left":
                    worldX -= speed * 2;
                    break;
                case "right":
                    worldX += speed * 2;
                    break;
            }

            if (slideCounter == 0) {
                touchSoap = false; // Alunecarea s-a încheiat
            }
        }
        if(pionezeCounter > 0) {
            pionezeCounter --;
            switch (direction) {
                case "up":
                    break;
                case "down":
                    break;
                case "left":
                    break;
                case "right":
                    break;
            }
            if (pionezeCounter == 0) {
                touchPioneze = false; // Alunecarea s-a încheiat
            }
        }
        if(attacking) {
            attacking();
        }
        else if(input.upPressed() || input.downPressed() || input.rightPressed()
                || input.leftPressed() || input.enterPressed()) {
            if(input.upPressed()) {
                direction = "up";
            } else if(input.downPressed()) {
                direction = "down";
            } else if(input.rightPressed()) {
                direction = "right";
            } else if(input.leftPressed()) {
                direction = "left";
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);
            if (!canTouchEvent) {
                // Daca ne-am departat suficient de eventul trecut se poate declansa altul
                int eventDistance = Math.max(Math.abs(worldX - previousEventX), Math.abs(worldY - previousEventY));
                if (eventDistance > gp.tileSize) {
                    canTouchEvent = true;
                }
            }

            //CHECK NPC COLLISION
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // CHECK
            // MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // CHECK EVENT
            gp.evHandler.checkEvent();

            // DACA COLIZIUNEA ESTE FALSA, JUCATORUL SE POATE DEPLASA
            if(!collisionOn && !input.enterPressed()) {
                int boostBonus = (boostTimer > 0 ? 4 : 0);
                switch(direction){
                    case "up": {
                        worldY -= speed + boostBonus;
                        break;

                    }
                    case "down": {
                        worldY += speed + boostBonus;
                        break;
                    }
                    case "left": {
                        worldX -= speed + boostBonus;
                        break;
                    }
                    case "right": {
                        worldX += speed + boostBonus;
                        break;
                    }
                }
            }
            //gp.input.enterPressed = false;
            spriteCounter++;
            if(spriteCounter > 10){ // se schimba sprite-ul odata la fiecare 10 cadre
                if (input.enterPressed() && !attacking) spriteNum = 5;
                else switch (spriteNum) {
                    case 1: spriteNum = 4; break;
                    case 4: spriteNum = 2; break;
                    case 2: spriteNum = 3; break;
                    default: spriteNum = 1; break;
                }
                spriteCounter = 0;
            }
        }
        else {
            spriteNum = 5;
            if (input.selectionKey1Pressed() && hasToy) {
                activeWeapon = Player.TOY;
            }
            if (input.selectionKey2Pressed() && hasBook) {
                activeWeapon = Player.BOOK;
            }
        }
        if (input.shotKeyPressed() && !gp.shotWasPressed && activeWeapon != 0 && weaponTimer > 50 * activeWeapon) {
            switch (activeWeapon) {
                case TOY:
                    currentProj = projectile;
                    break;
                case BOOK:
                    currentProj = projectile2;
                    break;
            }
            if (currentProj != null && !currentProj.alive && currentProj.haveResource(this)) {
                currentProj.set(worldX, worldY, direction, true, this);
                currentProj.substractResource(this);
                db.updateManna(manna);
                gp.projectileList.add(currentProj);
                weaponTimer = 0;
            }
            gp.shotWasPressed = true;
        }
        if (!input.shotKeyPressed() && gp.shotWasPressed) gp.shotWasPressed = false;
        if(invincible) {
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        if(life <= 0) {
            gp.gameState = gp.gameOverState;
            gp.stopMusic();
            gp.playSE(12);
        }
        weaponTimer++;
        if (boostTimer > 0) boostTimer--;
    }

    public void attacking() {
        spriteCounter++;
        if (direction == "up" || direction == "down") {

            if (spriteCounter <= 20) // aratam animatiile in intervalele de cadre propuse
            {
                spriteNum = 1;
            }
            if (spriteCounter > 20 && spriteCounter <= 40) {
                spriteNum = 2;

                // Salveaza curentuk worldX, wolrldY, solidArea
                int currentWorldX = worldX;
                int currentWorldY = worldY;
                int solidAreaWidth = solidArea.width;
                int solidAreaHeight = solidArea.height;

                // Ajusteaza worldX/Y al jucatorului pentru attackArea
                switch(direction)
                {

                    case "left": worldX -= attackArea.width; break;
                    case "right": worldX += attackArea.width; break;
                }
                // attackArea devine solidArea
                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;
                // verifica coliziunea cu monstri cu noile worldX, worldY si solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                damageMonster(monsterIndex, 2);

                // Dupa ce am verificat coliziunea, restauram informatiile originale
                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
            }

            if (spriteCounter > 40) {
                spriteNum = 1;
                spriteCounter = 0;
                attacking = false;
            }
        } else {
            if (spriteCounter <= 5) {
                spriteNum = 1;
            }

            if (spriteCounter > 5 && spriteCounter <= 20) {
                spriteNum = 2;
                // Salveaza curentul worldX, wolrldY, solidArea
                int currentWorldX = worldX;
                int currentWorldY = worldY;
                int solidAreaWidth = solidArea.width;
                int solidAreaHeight = solidArea.height;

                // Ajusteaza worldX/Y al jucatorului pentru attackArea
                switch(direction) {
                    case "left": worldX -= attackArea.width; break;
                    case "right": worldX += attackArea.width; break;
                }
                // attackArea devine solidArea
                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;
                // verifica coliziunea cu monstri cu noile worldX, worldY si solidArea
                int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
                damageMonster(monsterIndex, 2);

                // Dupa ce am verificat coliziunea, restauram informatiile originale
                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
            }
            if (spriteCounter > 20 && spriteCounter <= 35) {
                spriteNum = 3;
            }
            if (spriteCounter > 35) {
                spriteNum = 1;
                spriteCounter = 0;
                attacking = false;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        if(slideCounter > 0) {
            image = slide;
        }
        else {
            switch (direction) {
                case "up": {
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackUp1;
                        }
                        else {
                            image = attackUp2;
                        }
                    }
                    else if(pionezeCounter > 0) {
                        image = screamUp;
                    }
                    else image = switch (spriteNum) {
                        case 1 -> up1;
                        case 2 -> up2;
                        case 3 -> up3;
                        case 4 -> up4;
                        case 5 -> up5;
                        default -> image;
                    };
                    break;
                }
                case "down": {
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackDown1;
                        }
                        else {
                            image = attackDown2;
                        }
                    }
                    else if(pionezeCounter > 0) {
                        image = screamDown;
                    }
                    else image = switch (spriteNum) {
                        case 1 -> down1;
                        case 2 -> down2;
                        case 3 -> down3;
                        case 4 -> down4;
                        case 5 -> down5;
                        default -> image;
                    };
                    break;
                }
                case "left": {
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackLeft1;
                        }
                        else {
                            image = attackLeft2;
                        }
                    } else if (pionezeCounter > 0) {
                        image = screamLeft;
                    } else image = switch (spriteNum) {
                        case 1 -> left1;
                        case 2 -> left2;
                        case 3 -> left3;
                        case 4 -> left4;
                        case 5 -> left5;
                        default -> image;
                    };
                    break;
                }
                case "right": {
                    if (attacking) {
                        if (spriteNum == 1) {
                            image = attackRight1;
                        }
                        else {
                            image = attackRight2;
                        }
                    }
                    else if(pionezeCounter > 0) {
                        image = screamRight;
                    }
                    else image = switch (spriteNum) {
                        case 1 -> right1;
                        case 2 -> right2;
                        case 3 -> right3;
                        case 4 -> right4;
                        case 5 -> right5;
                        default -> image;
                    };
                    break;
                }
                // se schimba sprite-ul odata la fiecare 10 cadre
            }
        }
        if (invincible) {
            attackCounter--;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.2f));
            g2.drawImage(image, screenX, screenY, null);
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        // deseneaza solid area al obiectului (pentru testare)
        if (gp.debugMode) {
            g2.setColor(Color.red);
            g2.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }

    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[gp.currentMap][i].name; // instantiem numele fiecarui obiect

            switch(objectName) {
                case "EntryDoor1":
                case "EntryDoor2":
                    if(hasKey && gp.monster[gp.currentMap][1] == null) {
                        gp.playSE(4);
                        gp.obj[gp.currentMap][i] = null;
                        gp.ui.showMessage("You opened the door!");
                        hasKey = false;
                        teleport(1, 4, 7);
                        level = gp.currentMap;
                        hasTalkedToButch = false;
                        db.updateManna(manna);
                        db.updateLevel(level);
                        db.updateLife(life);
                        db.updateHasTalkedToButch(hasTalkedToButch);
                        db.updateKey(hasKey);
                        db.updateToy(hasToy);
                        db.updateBook(hasBook);
                    } else if(hasKey && gp.monster[gp.currentMap][1].dying == false){
                        gp.ui.showMessage("You can't enter the house before you beat the dog!");}
                    else if(!hasKey)
                    {
                        gp.ui.showMessage("You need the key!");
                    }
//                    updateDataBase(level);
                    break;
                case "EntryDoor3":
                    if(hasKey && gp.monster[gp.currentMap][1] == null) {
                        gp.ui.showMessage("You opened the door!");
                        gp.playSE(4);
                        gp.stopMusic();
                        gp.playMusic(11);
                        teleport(2, 7, 3);
                        hasKey = false;
                        level = gp.currentMap;
                        db.updateManna(manna);
                        db.updateLevel(level);
                        db.updateLife(life);
                        db.updateKey(hasKey);
                        db.updateToy(hasToy);
                        db.updateBook(hasBook);
                    }
                    else if(hasKey && gp.monster[gp.currentMap][1].dying == false)
                    {
                        gp.ui.showMessage("Lightning must be annihilated! Mammy Two Shoes can't stand him anymore!");

                    }
                    else if(!hasKey)
                    {
                        gp.ui.showMessage("You need the key!");
                    }
                    break;
                case "Key":
                    hasKey = true;
                    db.updateKey(hasKey);
                    gp.obj[gp.currentMap][i] = null;
                    gp.ui.showMessage("You found the key!");
                    break;
                case "Beef":
                case "Orange":
                case "Apple":
                    score += 200;
                    gp.playSE(2);
                    boostTimer += 500;
                    gp.obj[gp.currentMap][i] = null;
                    life = maxLife;
                    db.updateLife(life);
                    db.updateScore(score);
                    break;
                case "Soap1":
                case "Soap2":
                case "Banana1":
                case "Banana2":
                    gp.playSE(7);
                    touchSoap = true;
                    slideCounter = 15;
                    gp.obj[gp.currentMap][i] = null;
                    life -= 1;
                    boostTimer = Math.max(boostTimer - 200, 0);
                    db.updateLife(life);
                    break;
                case "Pioneze":
                    gp.playSE(5);
                    touchPioneze = true;
                    pionezeCounter = 15;
                    gp.obj[gp.currentMap][i] = null;
                    life -= 1;
                    boostTimer = Math.max(boostTimer - 200, 0);
                    db.updateLife(life);
                    break;
                case "Torch":
                    hasTorch = true;
                    gp.obj[gp.currentMap][i] = null;
                    break;
                case "DirtyBasket":
                    if (!canTouchEvent) break;
                    gp.gameState = gp.dialogueState;
                    if(gp.currentMap == 1){
                        gp.ui.currentDialogue = "You reached the dirty basked! You found new toys!\n(Progress has been saved)";
                    }
                    else {
                        gp.ui.currentDialogue = "You've reached one of the dirty baskets of Mammy Two Shoes.\n Ai noi jucarii!\n";
                    }
                    gp.player.manna = gp.player.maxManna;
                    gp.player.db.updateManna(gp.player.manna);
                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                    canTouchEvent = false;
                    break;
            }
        }
    }

    public void interactNPC(int i){
        if(input.enterPressed() && !gp.enterWasPressed) {
            gp.enterWasPressed = true;
            if(i!=999) {
                gp.gameState = gp.dialogueState;
                gp.npc[gp.currentMap][i].speak();
            }
            else {
                gp.playSE(6);
                attacking = true;
            }
        }
    }

    public void contactMonster(int i){
        if(i != 999) {
            if(!invincible && gp.monster[gp.currentMap][i].dying == false) {
                if(i == 1){
                    if(life > 1) gp.playSE(5);
                    life -= 2;
                    db.updateLife(life);
                }
                else{
                    if(life > 0) gp.playSE(5);
                    life -= 1;
                    db.updateLife(life);
                }
                invincible = true;
            }
        }
    }

    public void damageMonster(int i, int attack) {
        if(i != 999){
            if(!gp.monster[gp.currentMap][i].invincible) {
                gp.monster[gp.currentMap][i].life -= attack;
                gp.monster[gp.currentMap][i].invincible = true;
                gp.monster[gp.currentMap][i].damageReaction();

                if(gp.monster[gp.currentMap][i].life <= 0){
                    gp.monster[gp.currentMap][i].dying = true;
                    if(gp.monster[gp.currentMap][i].name=="Jerry")
                    {
                        gp.monster[gp.currentMap][i]=null;
                        score += 1000;
                        db.updateScore(score);
                        gp.stopMusic();
                        gp.playSE(8);
                        gp.gameState = gp.winState;
                    }
                    else
                    {
                        score += 100;
                        gp.monster[gp.currentMap][i]=null;
                        db.updateScore(score);
                    }
                }
            }
        }
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        worldX = gp.tileSize * col;
        worldY = gp.tileSize * row;
        gp.playSE(4);
    }

    @Override
    public void getImage() {}
}



