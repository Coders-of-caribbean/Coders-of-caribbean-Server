package org.unical.server.predicates.actions;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Id("move")
public class Move implements Action {
    @Param(0)
    private int speed;

    @Param(1)
    private int x;

    @Param(2)
    private int y;

    @Override
    public String toString() {
        return String.format("MOVE %d %d %d", speed, x, y);
    }
}

