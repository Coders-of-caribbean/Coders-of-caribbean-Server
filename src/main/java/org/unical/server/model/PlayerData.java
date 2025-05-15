package org.unical.server.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PlayerData {
    PlayerShip playerInfo;
    private List<Barrel> barrels;
    private List<Mine> mines;
    private List<EnemyShip> enemiesInfo;
}
