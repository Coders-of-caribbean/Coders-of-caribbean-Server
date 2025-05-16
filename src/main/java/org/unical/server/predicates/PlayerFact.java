package org.unical.server.predicates;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.*;
import org.unical.server.model.Ship;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Id("player")
public class PlayerFact {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int health;

    @Param(3)
    private int speed;

    @Param(4)
    private int direction;

    public PlayerFact(Ship ship) {
        this.x = ship.x;
        this.y = ship.y;
        this.health = ship.getRum();
        this.speed = ship.getSpeed();
        this.direction = ship.getDirection();
    }
}
