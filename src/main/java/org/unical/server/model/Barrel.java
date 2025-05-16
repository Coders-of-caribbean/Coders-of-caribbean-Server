package org.unical.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

import java.awt.*;

//EXTENSIBLE!
@Data
@Setter
public class Barrel extends Point {
    private int rum;
}
