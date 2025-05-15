package org.unical.server.predicates.objects;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.unical.server.model.Barrel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Id("rum")
public class Rum {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int quantity;

    public Rum(Barrel barrel) {
        this.x = barrel.x;
        this.y = barrel.y;
        this.quantity = barrel.getRum();
    }
}
