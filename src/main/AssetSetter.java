package main;

import entity.*;
import monster.*;
import object.*;

public class AssetSetter {
    GamePanel gp;
    MonsterFactory dogFactory;
    MonsterFactory fulgerFactory;
    MonsterFactory parrotFactory;
    MonsterFactory jonesFactory;
    MonsterFactory jerryFactory;
    MonsterFactory puppyFactory;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
        this.dogFactory = new DogFactory();
        this.jerryFactory = new JerryFactory();
        this.jonesFactory = new JonesFactory();
        this.fulgerFactory = new FulgerFactory();
        this.parrotFactory = new ParrotFactory();
        this.puppyFactory = new PuppyFactory();
    }

    public void setObject() {
        // LEVEL 1
        int mapNum = 0;
        int i = 0;

        gp.obj[mapNum][i] = new EntryDoor1(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new EntryDoor2(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 8 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Apple(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 55 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Beef(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 21 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Beef(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 6 * gp.tileSize;
        i++;

        if(!gp.player.hasKey) {
            gp.obj[mapNum][i] = new Key(gp);
            gp.obj[mapNum][i].worldX = 36 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
            i++;
        }

        gp.obj[mapNum][i] = new Beef(gp);
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 30 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 49 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 29 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 48 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 47 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 14 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 19 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 44 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Beef(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 47 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Beef(gp);
        gp.obj[mapNum][i].worldX = 45 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 43 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 12 * gp.tileSize;

        // LEVEL 2
        mapNum = 1;
        i = 0;

        gp.obj[mapNum][i] = new EntryDoor3(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 56 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 35 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 15 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 41 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 13 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap1(gp);
        gp.obj[mapNum][i].worldX = 14 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 18 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 49 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap1(gp);
        gp.obj[mapNum][i].worldX = 45 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap1(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 50 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap1(gp);
        gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 47 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 37 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap2(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Soap1(gp);
        gp.obj[mapNum][i].worldX = 22 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 43 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Apple(gp);
        gp.obj[mapNum][i].worldX = 8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 5 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Apple(gp);
        gp.obj[mapNum][i].worldX = 9 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 16 * gp.tileSize;
        i++;

        if(!gp.player.hasKey) {
            gp.obj[mapNum][i] = new Key(gp);
            gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 54 * gp.tileSize;
            i++;
        }

        if(!gp.player.hasTorch) {
            gp.obj[mapNum][i] = new Torch(gp);
            gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
            gp.obj[mapNum][i].worldY = 12 * gp.tileSize;
            i++;
        }

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 13 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 23 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 24 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 17 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 4 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new DirtyBasket(gp);
        gp.obj[mapNum][i].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 25 * gp.tileSize;

        // LEVEL 3
        mapNum = 2;
        i = 0;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX = 12 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 10 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Orange(gp);
        gp.obj[mapNum][i].worldX = 16 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 7 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 9 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Orange(gp);
        gp.obj[mapNum][i].worldX = 47 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 33 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX =  8 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 39 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Orange(gp);
        gp.obj[mapNum][i].worldX = 5 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 52 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX =  30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 49 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX = 30 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 42 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Banana1(gp);
        gp.obj[mapNum][i].worldX = 25 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 48 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Banana2(gp);
        gp.obj[mapNum][i].worldX = 20 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 50 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Banana1(gp);
        gp.obj[mapNum][i].worldX = 34 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 45 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Banana1(gp);
        gp.obj[mapNum][i].worldX = 44 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 27 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Orange(gp);
        gp.obj[mapNum][i].worldX = 50 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 54 * gp.tileSize;
        i++;

        gp.obj[mapNum][i] = new Pioneze(gp);
        gp.obj[mapNum][i].worldX = 29 * gp.tileSize;
        gp.obj[mapNum][i].worldY = 26 * gp.tileSize;
    }

    public void setMonster() {
        // LEVEL 1
        int mapNum = 0;
        int i = 0;
        // metoda din AssetSetter nu trebuie sa stie detaliile
        // despre cum este creat un monstru
        // tot ce trebuie sa stie este ca va returna un obiect de
        // tip Entity

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 48;
        gp.monster[mapNum][i].worldY = gp.tileSize * 48;
        i++;

        gp.monster[mapNum][i] = dogFactory.createMonster(MonsterFactory.MonsterType.DOG, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 14;
        gp.monster[mapNum][i].worldY = gp.tileSize * 11;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 22;
        gp.monster[mapNum][i].worldY = gp.tileSize * 44;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 41;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 48;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 46;
        gp.monster[mapNum][i].worldY = gp.tileSize * 50;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 42;
        gp.monster[mapNum][i].worldY = gp.tileSize * 50;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 44;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 48;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 47;
        gp.monster[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 43;
        gp.monster[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 41;
        gp.monster[mapNum][i].worldY = gp.tileSize * 14;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 10;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 27;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 22;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 23;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 7;
        gp.monster[mapNum][i].worldY = gp.tileSize * 29;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 34;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 36;
        gp.monster[mapNum][i].worldY = gp.tileSize * 8;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 24;
        gp.monster[mapNum][i].worldY = gp.tileSize * 33;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 31;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 8;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 9;
        gp.monster[mapNum][i].worldY = gp.tileSize * 32;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 6;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;

        gp.monster[mapNum][i] = puppyFactory.createMonster(MonsterFactory.MonsterType.PUPPY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;

        // LEVEL 2
        mapNum = 1;
        i = 0;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 15;
        gp.monster[mapNum][i].worldY = gp.tileSize * 53;
        i++;

        gp.monster[mapNum][i] = fulgerFactory.createMonster(MonsterFactory.MonsterType.FULGER, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 53;
        i++;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 17;
        gp.monster[mapNum][i].worldY = gp.tileSize * 18;
        i++;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 5;
        i++;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 45;
        gp.monster[mapNum][i].worldY = gp.tileSize * 12;
        i++;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 46;
        gp.monster[mapNum][i].worldY = gp.tileSize * 17;
        i++;

        gp.monster[mapNum][i] = jonesFactory.createMonster(MonsterFactory.MonsterType.JONES, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 6;

        // LEVEL 3
        mapNum = 2;
        i = 0;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 25;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;

        gp.monster[mapNum][i] = jerryFactory.createMonster(MonsterFactory.MonsterType.JERRY, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 49;
        gp.monster[mapNum][i].worldY = gp.tileSize * 54;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 35;
        gp.monster[mapNum][i].worldY = gp.tileSize * 9;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 16;
        gp.monster[mapNum][i].worldY = gp.tileSize * 35;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 47;
        gp.monster[mapNum][i].worldY = gp.tileSize * 36;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 47;
        gp.monster[mapNum][i].worldY = gp.tileSize * 30;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 7;
        gp.monster[mapNum][i].worldY = gp.tileSize * 44;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 30;
        gp.monster[mapNum][i].worldY = gp.tileSize * 50;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 20;
        gp.monster[mapNum][i].worldY = gp.tileSize * 54;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 47;
        gp.monster[mapNum][i].worldY = gp.tileSize * 54;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 38;
        gp.monster[mapNum][i].worldY = gp.tileSize * 52;
        i++;

        gp.monster[mapNum][i] = parrotFactory.createMonster(MonsterFactory.MonsterType.PARROT, gp);
        gp.monster[mapNum][i].worldX = gp.tileSize * 7;
        gp.monster[mapNum][i].worldY = gp.tileSize * 47;
    }

    public void setNPC(){
        int mapNum = 0;
        int i = 0;

        gp.npc[mapNum][i] = new Butch(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 18;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;

        mapNum = 1;
        i++;
        gp.npc[mapNum][i] = new Water1(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 31;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;

        i++;
        gp.npc[mapNum][i] = new Water2(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 32;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;

        i++;
        gp.npc[mapNum][i] = new Water3(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 33;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;

        i++;
        gp.npc[mapNum][i] = new Water4(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 34;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;

        i++;
        gp.npc[mapNum][i] = new Water5(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 35;
        gp.npc[mapNum][i].worldY = gp.tileSize * 49;
        i++;
        gp.npc[mapNum][i] = new Water6(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 35;
        gp.npc[mapNum][i].worldY = gp.tileSize * 48;

        mapNum = 2;
        i ++;

        gp.npc[mapNum][i] = new Pickels(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize * 7;
        gp.npc[mapNum][i].worldY = gp.tileSize * 9;

    }
}
