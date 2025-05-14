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
@Id("bomb")
public class Bomb {
    @Param(0)
    private int x;

    @Param(1)
    private int y;
}
