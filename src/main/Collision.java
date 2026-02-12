package main;

import entity.Entity;

public class Collision {
    GamePanel gp;
    public Collision(GamePanel gp)
    {
        this.gp = gp;
    }

    // checkTile -> verifica daca coliziunea intre o entitate si tile-uri
    // calculeaza coordonatele colturilor dreptunghiului de coliziune(solidArea) al entitatii
    // in coordonatele globale pe harta (worldX, wolrdY)
    // verificam daca tile-urile respective sunt setate cu coliziune
    public void checkTile(Entity entity) { // Aceasta functie afla punctele din cadrul personajului care marcheaza coliziunea
        // astfel incat coliziunea sa fie una mai fireasca si sa se intample in momentul in
        // care exista contact cat mai realist cu obiectul ce are coliziunea activata,
        // si nu sa fie una in care sa fie cativa pixeli buni distanta de obiectul coliziune
        // si deja personajul sa nu se poata deplasa in directia respectiva

        // calculam zona solid a entitatii
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = (entityLeftWorldX) / gp.tileSize;
        int entityRightCol = (entityRightWorldX) / gp.tileSize;
        int entityTopRow = (entityTopWorldY) / gp.tileSize;
        int entityBottomRow = (entityBottomWorldY)/ gp.tileSize;

        int tileNum1, tileNum2; // avem nevoie doar de doua "colturi" ale patratului care reprezinta solidArea
        // pentru ca respectiva coliziune sa fie verificata

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                // tile-urile sunt plasate fix pe o grila, deci este mai simplu sa
                // verificam daca tile-ul respectiv este coliziune sau nu
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;  // daca unul dintre colturi atinge
                    // zona solida a tile-ului
                    // are loc coliziunea
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }


    // Verifica coliziunea intre entitate si obiecte

    public int checkObject(Entity entity, boolean player) // returneaza indexul in cazul in care
    {
        int index = 999;

        for(int i = 0; i < gp.obj[1].length; i ++)
        // Itereaza toate obiectele din harta curenta
        {
            if(gp.obj[gp.currentMap][i] != null)
            {
                // Obtine pozitia solid area a entitatii
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Obtine pozitia solid area a obiectului
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entity.direction) {// folosim intersects deoarece spre deosebire
                    // de checkTile, nu este nevoie decat sa scanam
                    // 10, eventual cateva zeci de obiecte ceea ce
                    // este putin in comparatie cu scanarea intregii harti
                    // in functie de directia de miscae a entitatii
                    // muta zona solida putin mai in fata
                    // ca sa vad prevada coliziunea in directia de deplasare
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if (entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                    //System.out.println("down collision");
                    if (gp.obj[gp.currentMap][i].collision == true) {
                        entity.collisionOn = true;
                    }
                    if (player == true) {
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX; // resetam x si y pentru entitate si obiect
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index; // intoarcem indexul obiectului cu care s-a produs coliziunea
    }
    // NPC SAU MONSTRU
    public int checkEntity(Entity entity, Entity[][] target){
        int index = 999;

        for(int i = 0; i < target[1].length; i ++)
        {
            if(target[gp.currentMap][i] != null)
            {
                // Obtine pozitia solid area a entitatii
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // Obtine pozitia solid area a obiectului
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea))
                {
                    if(target[gp.currentMap][i] != entity) { // daca se "ciocneste" si
                        // nu cu ea insasi
                        entity.collisionOn = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
            }
        }

        return index;
    }

    public boolean checkPlayer(Entity entity)
    {

        boolean contactPlayer = false;
        // Obtine pozitia solid area a entitatii
        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;
        // Obtine pozitia solid area a obiectului
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entity.direction) {
            case "up":
                entity.solidArea.y -= entity.speed;
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                break;
            case "right":
                entity.solidArea.x += entity.speed;
                break;
        }
        if (entity.solidArea.intersects(gp.player.solidArea)) {
            entity.collisionOn = true;
            contactPlayer = true;
        }
        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;

        return contactPlayer;
    }

}
