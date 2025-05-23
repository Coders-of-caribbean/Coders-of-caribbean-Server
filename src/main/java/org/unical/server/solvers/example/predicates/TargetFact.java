package org.unical.server.solvers.example.predicates;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Id("choose")
public class TargetFact {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int speed;
}
