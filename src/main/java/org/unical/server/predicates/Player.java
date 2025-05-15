package org.unical.server.predicates;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unical.server.model.PlayerShip;
import org.unical.server.model.Ship;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Id("player")
public class Player {
    @Param(0)
    private int id;

    @Param(1)
    private int x;

    @Param(2)
    private int y;

    @Param(3)
    private int health;

    @Param(4)
    private int speed;

    @Param(5)
    private int direction;

    public Player(Ship ship) {
        this.x = ship.x;
        this.y = ship.y;
        this.health = ship.getRum();
        this.speed = ship.getSpeed();
        this.direction = ship.getDirection();
    }
}
