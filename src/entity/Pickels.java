package entity;

import main.GamePanel;
import java.util.Random;

public class Pickels extends Entity{
    public Pickels(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        solidArea.x = 20;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 80;
        solidArea.height = 80;
        // AICI SE SCHIMBA ZONA SOLIDA IN CAZ CA SE DORESTE, CUM E LA TOM
        getImage();
        setDialogue();
    }
    public void getImage () {

        up1 = setup("/npc/Pickles/Pickels_U1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/Pickles/Pickels_U2", gp.tileSize, gp.tileSize);
        up3 = setup("/npc/Pickles/Pickels_U3", gp.tileSize, gp.tileSize);
        up4 = setup("/npc/Pickles/Pickels_U4", gp.tileSize, gp.tileSize);
        up5 = setup("/npc/Pickles/Pickels_standU", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/Pickles/Pickels_D1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/Pickles/Pickels_D2", gp.tileSize, gp.tileSize);
        down3 = setup("/npc/Pickles/Pickels_D3", gp.tileSize, gp.tileSize);
        down4 = setup("/npc/Pickles/Pickels_D4", gp.tileSize, gp.tileSize);
        down5 = setup("/npc/Pickles/Pickels_standD", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/Pickles/Pickels_R1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/Pickles/Pickels_R2", gp.tileSize, gp.tileSize);
        right3 = setup("/npc/Pickles/Pickels_R3", gp.tileSize, gp.tileSize);
        right4 = setup("/npc/Pickles/Pickels_R4", gp.tileSize, gp.tileSize);
        right5 = setup("/npc/Pickles/Pickels_standR", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/Pickles/Pickels_L1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/Pickles/Pickels_L2", gp.tileSize, gp.tileSize);
        left3 = setup("/npc/Pickles/Pickels_L3", gp.tileSize, gp.tileSize);
        left4 = setup("/npc/Pickles/Pickels_L4", gp.tileSize, gp.tileSize);
        left5 = setup("/npc/Pickles/Pickels_standL", gp.tileSize, gp.tileSize);

    }
    public void setDialogue()
    {
        dialogues[0] = "Hello, friend!";
        dialogues[1] = "I have a big problem.\nMy favorite book is in Mommy TwoShoes' room.";
        dialogues[2] = "I can't reach it. I'm afraid of the birds.\n" +
                "Help me find it!";
        dialogues[3] = "I will be eternally grateful to you! I'm waiting for you with the book.";
        dialogues[4] = "Thank you! You've made it this far and I can only congratulate you!";
        dialogues[5] = "The last room in which Jerry spends his days \nis also the most dangerous.";
        dialogues[6] = "This area of the house is full of bookshelves.\n" +
                "Take as many as you can carry with you." ;
        dialogues[7] = "By sending them towards your opponents,\n" +
                "you can knock them away. Press the F key to succeed!";
        dialogues[8] = "Good luck on this last hop, you'll need it!";

    }
    public void setAction() {

        actionLockCounter ++;

        if(actionLockCounter == 60)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // genereaza numere de la 1 la 100

            if(i <= 15)
            {
                direction = "up";
            }
            if(i > 15 && i <= 30)
            {
                direction = "down";
            }
            if(i > 30 && i <= 45)
            {
                direction = "left";
            }
            if(i > 45 && i <= 60)
            {
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    public void speak()
    {

        if (gp.player.hasTalkedToButch) {
            gp.ui.currentDialogue = "We've already talked, neighbor.!";
            return;
        }

        if(dialogueIndex >= 4 && !gp.player.hasCollectedBook){
            gp.ui.currentDialogue = "I will be eternally grateful to you! I'm waiting for you with the book.";
            return;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        if(dialogueIndex == 7){
            Player.hasBook = true;
            Player.activeWeapon = 2;
            gp.player.db.updateBook(true);
        }
        if(dialogueIndex < 8) dialogueIndex ++;
        else {
            gp.player.hasTalkedToButch = true;
        }
        super.speak();
    }

}

