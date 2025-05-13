package org.unical.server.solvers.tommardo.facts;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Id("me")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerFact {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int speed;
}
