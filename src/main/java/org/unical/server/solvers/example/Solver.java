package org.unical.server.solvers.example;

import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.Input;
import org.unical.server.model.PlayerData;


@Component("terracina panca piana 5*30")
public class Solver extends AbstractSolver {
    @Override
    public String solve(PlayerData input) {
        return "Ninja Fuduli";
    }
}
