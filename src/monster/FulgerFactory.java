// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class FulgerFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.FULGER) {
            return new Fulger(gp);
        }
        return null;
    }
}
