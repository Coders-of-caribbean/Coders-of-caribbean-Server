package org.unical.server.solvers.tommardo.sol;

import org.springframework.stereotype.Component;
import org.unical.server.ResponseSolver;
import org.unical.server.model.Input;

@Component
public class Solver extends ResponseSolver {

    @Override
    public String solve(Input input) {
        return "borroto puzza!";
    }
}
