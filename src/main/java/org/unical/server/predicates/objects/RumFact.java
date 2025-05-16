package org.unical.server.predicates.objects;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;
import lombok.*;
import org.unical.server.model.Barrel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Id("rum")
public class RumFact {
    @Param(0)
    private int x;

    @Param(1)
    private int y;

    @Param(2)
    private int quantity;

    public RumFact(Barrel barrel) {
        this.x = barrel.x;
        this.y = barrel.y;
        this.quantity = barrel.getRum();
    }
}
