package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
public abstract class Ship extends Point{
    protected int speed;
    protected int rum;
    protected int direction;
}
