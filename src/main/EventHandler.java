package main;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    // Clasa asta e pe cale de disparitie)))
    public EventHandler(GamePanel gp) {
        this.gp = gp;
        // cream o matrice 3D, fiecare tile poate avea un eveniment atasat
        eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        int col = 0;
        int row = 0;
        int map = 0;
        // pentru fiecare tile creeam un EventRect mic in interiorul acelui tile
        while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[map][col][row] = new EventRect();
            eventRect[map][col][row].x = 25;
            eventRect[map][col][row].y = 25;
            eventRect[map][col][row].width = 2;
            eventRect[map][col][row].height = 2;
            eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;
            col ++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row ++;
                if(row == gp.maxWorldRow){
                    row = 0;
                    map ++;
                }
            }
        }
    }

    // metoda care se apeleaza in mod constant si verifica daca jucatorul
    // s-a apropiat suficent de un event nou
    public void checkEvent() {
        // Verifica daca jucatorul este la 1 tile distanta de ultimul event
        int xDistance = Math.abs(gp.player.worldX - previousEventX); // abs - distanta care poate sa fie si negativa, abs o face pozitiva
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance); // alege maximul dintre distantele dintre jucator si event-ul anterior
        if(distance > gp.tileSize) {
            canTouchEvent = true;
        }
        if(canTouchEvent == true) {
            if (hit(1, 29, 10, "left") == true) {
                NoMoreWater(gp.dialogueState);
                NoMoreWaterChecked();
            } else if (hit(1, 29, 10, "up") == true) {
                NoMoreWater(gp.dialogueState);
                NoMoreWaterChecked();
            } else if (hit(1, 29, 10, "right") == true) {
                NoMoreWater(gp.dialogueState);
                NoMoreWaterChecked();
            }
            else if(hit(2, 49, 35, "down") == true || hit(2, 48, 36, "right") == true){
                CollectedBook(gp.dialogueState);
            }
            else if (hit(2, 15, 9, "up") == true) MannaLibrary(gp.dialogueState);
            else if (hit(2, 48, 9, "up") == true) MannaLibrary(gp.dialogueState);
            else if (hit(2, 48, 36, "right") == true) MannaLibrary(gp.dialogueState);
            else if (hit(2, 6, 54, "left") == true) MannaLibrary(gp.dialogueState);
            else if (hit(2, 6, 45, "left") == true) MannaLibrary(gp.dialogueState);
            else if (hit(2, 5, 46, "up") == true) MannaLibrary(gp.dialogueState);
        }
    }

    public void NoMoreWaterChecked()
    {
        gp.tileM.setup(58, "floor_bath", false);
        gp.tileM.setup(59, "floor_bath", false);
        gp.tileM.setup(60, "floor_bath", false);
        //
        gp.tileM.setup(61, "floor_bath", false);
        gp.tileM.setup(62, "floor_bath", false);
        gp.tileM.setup(63, "floor_bath", false);
        gp.tileM.setup(64, "floor_bath", false);
        gp.tileM.setup(65, "floor_bath", false);
        gp.tileM.setup(66, "floor_bath", false);
        gp.tileM.setup(67, "floor_bath", false);
        gp.tileM.setup(68, "floor_bath", false);
        gp.tileM.setup(69, "floor_bath", false);
        gp.tileM.setup(70, "floor_bath", false);
        gp.tileM.setup(71, "floor_bath", false);
        gp.tileM.setup(72, "floor_bath", false);
        gp.tileM.setup(73, "floor_bath", false);
        gp.tileM.setup(74, "floor_bath", false);
        gp.tileM.setup(75, "floor_bath", false);
        gp.tileM.setup(80, "floor_bath", false);
//        gp.tileM.setup(77, "floor_bath", false);
        gp.tileM.setup(82, "floor_bath", false);
        gp.tileM.setup(83, "floor_bath", false);
        gp.npc[gp.currentMap][1] = null;
        gp.npc[gp.currentMap][2] = null;
        gp.npc[gp.currentMap][3] = null;
        gp.npc[gp.currentMap][4] = null;
        gp.npc[gp.currentMap][5] = null;
        gp.npc[gp.currentMap][6] = null;
    }

    public boolean hit(int map, int col, int row, String reqDirection) // Obtine coliziunea event-ului
    {
        boolean hit = false;
        if(map == gp.currentMap){
            // mutam "hitbox-u" jucatorului la pozitia sa absoluta in lume
            gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
            gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
            // lasam eventRect sa fie mutat pe pozitia sa reala pe harta
            eventRect[map][col][row].x = col * gp.tileSize + eventRect[map][col][row].x;
            eventRect[map][col][row].y = row * gp.tileSize + eventRect[map][col][row].y;

            // verifica daca hitbox-ul jucatorului se suprapune cu event-ul si daca directia lui este corecta
            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone == false) {
                if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any"))
                {
                    hit = true;

                    previousEventX = gp.player.worldX;
                    previousEventY = gp.player.worldY;
                }
            }
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
        }

        return hit;
    }

    public void NoMoreWater(int gameState){
        gp.gameState = gameState;
        if(gp.currentMap == 1){
            gp.ui.currentDialogue = "You've stopped the flooding water! Make sure you got the key and you get rid of Lighting!";
            gp.playSE(13);
        }
        canTouchEvent = false;
    }

    public void MannaLibrary(int gameState) {
        if (gp.player.hasCollectedBook && gp.player.hasTalkedToButch) {
            gp.gameState = gameState;
            gp.ui.currentDialogue = "You reached the bookshelf! You got new books!";
            gp.player.manna = gp.player.maxManna;
            gp.player.db.updateManna(gp.player.manna);
            canTouchEvent = false;
        }
        else{
            gp.ui.showMessage("Pickel's book is not here.");
        }
    }

    public void CollectedBook(int gameState){
        gp.gameState = gameState;
        if(gp.currentMap == 2){
            gp.ui.currentDialogue = "Well done! You've found Pickel's book! Give it back.";
            gp.player.hasCollectedBook = true;
            canTouchEvent = false;
        }
    }

    public void teleport(int map, int col, int row) {
        gp.currentMap = map;
        gp.player.worldX = gp.tileSize * col;
        gp.player.worldY = gp.tileSize * row;
        gp.playSE(4);
    }
}
