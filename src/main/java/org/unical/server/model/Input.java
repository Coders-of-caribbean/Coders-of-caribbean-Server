package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.List;

//Here we define the input recieved from the game
@Data
@NoArgsConstructor
public class Input {
    private List<Barrel> barrels;
    private List<Bomb> bombs;
    private List<Ship> ships;
}
