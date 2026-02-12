// DogFactory.java
package monster;

import entity.Entity;
import main.GamePanel;

public class DogFactory extends MonsterFactory {

    @Override
    protected Entity CreateMonster(MonsterType type, GamePanel gp) {
        if (type == MonsterType.DOG) {
            return new Dog(gp);
        }
        return null;
    }
}
