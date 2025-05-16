package org.unical.server.solvers.aspiranti;

import org.unical.server.model.*;
import org.unical.server.solvers.aspiranti.Solver;

import java.util.List;

public class SolverManager {

    public static void main(String[] args) {
        Solver solver = new Solver();
        PlayerData playerData = new PlayerData();

        Barrel barrel = new Barrel();
        barrel.x = 0;
        barrel.y = 0;
        barrel.setRum(5);

        Mine mine = new Mine();
        mine.x = 0;
        mine.y = 0;

        PlayerShip ship = new PlayerShip();
        ship.x = 0;
        ship.y = 0;
        ship.setRum(500);
        ship.setDirection(0);
        ship.setSpeed(5);

        EnemyShip enemy = new EnemyShip();
        enemy.x = 22;
        enemy.y = 25;
        enemy.setRum(500);
        enemy.setDirection(0);
        enemy.setSpeed(5);

        playerData.setBarrels(List.of(barrel));
        playerData.setMines(List.of(mine));
        playerData.setEnemiesInfo(List.of(enemy));
        playerData.setPlayerInfo(ship);
        System.out.println(solver.solve(playerData));
    }

}
