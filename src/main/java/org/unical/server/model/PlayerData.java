package org.unical.server.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlayerData {
    PlayerShip info;
    private List<Barrel> barrels;
    private List<Mine> mines;
    private List<EnemyShip> enemies;
}
