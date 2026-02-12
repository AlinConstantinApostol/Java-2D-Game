package entity;
import main.GamePanel;

public class Projectile extends Entity{
    Entity user;
    public Projectile(GamePanel gp){
        super(gp);
    }
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;
    }
    @Override
    public void getImage(){
    }
    public void update(){

        if(user == gp.player){
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if(monsterIndex != 999){
                gp.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        if(user != gp.player){
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if(gp.player.invincible == false && contactPlayer){
                damagePlayer(attack);
                switch (direction) {
                    case "left":
                        image = screamLeft;
                        break;
                    case "up":
                        image = screamUp;
                        break;
                    case "down":
                        image = screamDown;
                        break;
                    case "right":
                        image = screamRight;
                        break;
                }
                alive = false;
            }
        }

        switch(direction){ // miscarea proiectilelor
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life --;
        if(life <= 0){
            alive = false; // cat trimiti un projectile, gradual isi pierde viata
            // se misca 80 de cadre
        }

        if(projectileType == 2) {
            spriteCounter++;
            if (spriteCounter > 16) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 3;
                } else if (spriteNum == 3) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public boolean haveResource(Entity user) {
        boolean haveResource = false;
        return haveResource;
    }
    public void substractResource(Entity user) {}
}
