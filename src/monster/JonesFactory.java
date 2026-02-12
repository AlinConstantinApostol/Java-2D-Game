// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class JonesFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.JONES) {
            return new Jones(gp);
        }
        return null;
    }
}
