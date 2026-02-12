// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class PuppyFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.PUPPY) {
            return new Puppy(gp);
        }
        return null;
    }
}
