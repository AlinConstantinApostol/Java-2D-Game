// MonsterFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public abstract class MonsterFactory {

    public enum MonsterType {
        DOG, PUPPY, JONES, JERRY, FULGER, PARROT;
    }

    public Entity createMonster(MonsterType type, GamePanel gp) {
        Entity monster = CreateMonster(type, gp);
        monster.getImage();
        return monster;
    }

    protected abstract Entity CreateMonster(MonsterType type, GamePanel gp);
}
