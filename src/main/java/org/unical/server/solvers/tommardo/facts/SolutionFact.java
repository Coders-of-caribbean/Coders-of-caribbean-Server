package org.unical.server.solvers.tommardo.facts;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Id("sol")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SolutionFact {
    @Param(0)
    int x;
    @Param(1)
    int y;
    @Param(2)
    int speed;
}
