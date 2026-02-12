// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class JerryFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.JERRY) {
            return new Jerry(gp);
        }
        return null;
    }
}
