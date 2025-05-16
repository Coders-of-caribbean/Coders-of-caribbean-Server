package org.unical.server.predicates.objects;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.*;
import org.unical.server.model.Mine;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Id("mine")
public class MineFact {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    public MineFact(Mine mine){
        this.x = mine.x;
        this.y = mine.y;
    }
}
