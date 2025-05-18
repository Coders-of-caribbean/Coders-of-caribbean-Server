package org.unical.server.solvers.example;

import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.Input;
import org.unical.server.model.PlayerData;


@Component("aspiranti")
public class Solver extends AbstractSolver {
    @Override
    public String solve(PlayerData input) {
        return "2 12 1";
    }
}
