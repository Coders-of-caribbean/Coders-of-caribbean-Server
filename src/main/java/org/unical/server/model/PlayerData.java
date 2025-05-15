package org.unical.server.model;

import lombok.Getter;

import java.util.List;

@Getter
public class PlayerData {
    PlayerShip info;
    private List<Barrel> barrels;
    private List<Mine> mines;
    private List<EnemyShip> enemies;
}
