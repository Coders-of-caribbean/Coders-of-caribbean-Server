package org.unical.server.solvers.example;

import org.springframework.stereotype.Component;
import org.unical.server.ResponseSolver;
import org.unical.server.model.Input;


@Component("terracina panca piana 5*30")
public class Solver extends ResponseSolver {
    @Override
    public String solve(Input input) {
        return "Ninja Fuduli";
    }
}
