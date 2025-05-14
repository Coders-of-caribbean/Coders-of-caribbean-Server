package org.unical.server.solvers.sahur.predicates;

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
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int speed;

    @Override
    public String toString() {
        return String.format("MOVE %d %d %d", x, y, speed);
    }


}

