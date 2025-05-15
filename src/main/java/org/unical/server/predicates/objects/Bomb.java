package org.unical.server.predicates.objects;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unical.server.model.Mine;

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

    public Bomb(Mine mine){
        this.x = mine.x;
        this.y = mine.y;
    }
}
