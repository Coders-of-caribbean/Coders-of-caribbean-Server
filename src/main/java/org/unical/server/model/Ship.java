package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;

@Data
public class Ship extends Point{
    private String name;
    private int rum;
}
