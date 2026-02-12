// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class ParrotFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.PARROT) {
            return new Parrot(gp);
        }
        return null;
    }
}
