package org.unical.server.model;

import lombok.Data;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class PlayerData {
    PlayerShip playerInfo;
    private List<Barrel> barrels;
    private List<Mine> mines;
    private List<EnemyShip> enemiesInfo;
}
