package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class TileManager {
    GamePanel gp; // Referinta la panoul de joc
    public Tile[] tile;
    public int mapTileNum[][][]; // in acest

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[90]; // Initializeaza tabloul de dale
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow]; // Initializeaza matricea hartii
        getTileImage(); // Incarca imaginile pentru dale
        loadMap("/maps/world01.txt", 0); // Incarca harta dintr-un fisier text
        loadMap("/maps/world02.txt", 1); // Incarca harta dintr-un fisier text
        loadMap("/maps/world03.txt", 2); // Incarca harta dintr-un fisier text
    }

    public void draw(Graphics2D g2){
//        g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[1].image, 96, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*2, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*3, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*4, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*5, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*6, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*7, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*8, 0, gp.tileSize, gp.tileSize, null);
//        g2.drawImage(tile[2].image, 96*9, 0, gp.tileSize, gp.tileSize, null);
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow]; // informatiile din mapa au fost salvate in mapTileNum

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY -gp.player.worldY + gp.player.screenY; // daca pozitia jucatorului este de exemplu
            // 500:500, atunci pozitia dalei 0 va fi in
            // pozitia jucatorului - 500
            // + gp.player.screenX  este folosit cu rol
            // de offset intrucat, chiar daca jucatorul
            // este spawnat in coltul hartii, el este
            // in de fapt in centrul ecranului

            if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX &&
                    worldX < gp.player.worldX + gp.player.screenX + gp.tileSize &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY < gp.player.worldY + gp.player.screenY + gp.tileSize){
                g2.drawImage(tile[tileNum].image, screenX, screenY,  null); // index ul lui tile Array//                    g2.setColor(new Color(0, 255, 0, 128)); // Setează culoarea dreptunghiului (roșu semi-transparent)
                if (tile[tileNum].collision) {
                    g2.setColor(new Color(0, 255, 0, 128)); // Setează culoarea dreptunghiului (verde)
                    g2.setStroke(new BasicStroke(3)); // Setează grosimea liniei la 3 pixeli
                    if (gp.debugMode) {
                        g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize); // Desenează dreptunghiul
                    }
                }                                             // pe ecran
            } // acest if are rolul de seta marginile prin care un caracter se poate misca

            worldCol++;

            if(worldCol == gp.maxWorldCol)
            {
                worldCol = 0;

                worldRow ++;
            }
        }
    }
    public void getTileImage() {
        // LEVEL 1
        setup(0, "grass", false);
        setup(1, "plant1", true);
        setup(2, "plant2", true);
        setup(3, "plant3", true);
        setup(4, "bush", true);
        setup(5, "wall", true);
        setup(6, "window", false);
        //setup(7, "entrydoor1");
        //setup(8, "entrydoor2");
        //setup(9, "dirty_clothes");
        // LEVEL 2
        setup(10, "floor_bath", false);
        setup(28, "shower1", true);
        setup(11, "shower2", true);
        setup(12, "shower3", true);
        setup(13, "shower4", true);
        setup(14, "shower5", true);
        setup(15, "shower6", true);
        setup(16, "sink1", true);
        setup(17, "sink2", true);
        setup(18, "toilet1", true);
        setup(19, "toilet2", true);
        setup(20, "toilet3", true);
        setup(21, "toilet4", true);
        setup(22, "washed1", true);
        setup(23, "washed2", true);
        setup(24, "washed3", true);
        setup(25, "washed4", true);
        setup(26, "washed5", true);
        setup(27, "washed6", true);
        setup(29, "washing_machine1", true);
        setup(30, "washing_machine2", true);
        setup(57, "wet_floor1", true);
        setup(58, "wet_floor2", true);
        setup(59, "wet_floor3", true);
        setup(60, "wet_floor4", true);
        setup(61, "wet_floor5", true);

        setup(62, "1", true);
        setup(63, "2", true);
        setup(64, "3", true);
        setup(65, "4", true);
        setup(66, "5", true);
        setup(67, "6", true);
        setup(68, "7", true);
        setup(69, "8", true);
        setup(70, "9", true);
        setup(71, "10", true);
        setup(72, "11", true);
        setup(73, "12", true);
        setup(74, "13", true);
        setup(75, "14", true);
        setup(76, "up-down", true);
        setup(77, "top-left-curve", true);
        setup(78, "left-T", true);
        setup(79, "left-right", true);
        setup(80, "bottom-opening", true);
        setup(81, "valve", true);
        setup(82, "water_running1", true);
        setup(83, "water_running2", true);
        //setup(31, "dirty_clothes2");
        // LEVEL 3
        setup(38, "bed1", true);
        setup(32, "bed2", true);
        setup(33, "bed3", true);
        setup(34, "bed4", true);
        setup(35, "plant1", true);
        setup(36, "vase", true);
        setup(37, "chair", true);
        setup(39, "carpet", false);
        setup(40, "couch1", true);
        setup(41, "couch2", true);
        setup(42, "couch3", true);
        setup(43, "couch4", true);
        setup(44, "couch5", true);
        setup(45, "couch6", true);
        setup(46, "fotoliu1", true);
        setup(47, "fotoliu2", true);
        setup(48, "fotoliu3", true);
        setup(49, "fotoliu4", true);
        setup(50, "biblio1", true);
        setup(51, "biblio2", true);
        setup(52, "ceas1", true);
        setup(53, "ceas2", true);
        setup(54, "tv", true);
        setup(55, "white_table", true);
        setup(56, "red_table", true);
        setup(84, "biblio3", true);
        setup(85, "biblio4", true);
    }

    public void loadMap(String filePath, int map){
        // incarca layout-ul nivelului dintr-un fisier .txt
        try{
            InputStream is = getClass().getResourceAsStream(filePath); // importa fisierul
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // citeste continutul textului

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine(); // citeste linia unui text si o pune in string ul line
                while(col < gp.maxWorldCol){
                    String numbers[] = line.split(" "); // imparte string ul dupa spatii

                    int num = Integer.parseInt(numbers[col]); // conversie din string in int

                    mapTileNum[map][col][row] = num; // salveaza numarul in matrice
                    col ++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row ++;
                }
            }
            br.close();
        }
        catch(Exception e) {

        }
    }

    public void setup(int index, String imagePath, boolean collision){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath +".png"));
            // ImageIO.read -> citeste si returneaza o imagine de tip "BufferedImage" dintr-un flux de intrare
            // getClass().getResourceAsStream() - returneaza flux de intrare pentru resursa specificata
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            // redimensioneaza imaginea la dimensiunile date
            tile[index].collision = collision;
        } catch (IOException e){ // gestioneaza eventualele exceptii de tipul IOException care pot aparea
            // atunci cand se lucreaza cu intrari/ iesiri I/0, in acest caz, la citirea
            // imaginii de pe disc
            e.printStackTrace();
        }
    }

}
