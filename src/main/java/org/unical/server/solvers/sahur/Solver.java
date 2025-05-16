package org.unical.server.solvers.sahur;


import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {

    public Solver() {
        super();
    }

    @Override
    public String solve(PlayerData input) {
        InputProgram inputProgram = new ASPInputProgram();
        try {
            handler.removeAll();
            inputProgram.addProgram("move(1).");
            handler.addProgram(inputProgram);
            addFacts(inputProgram, input);

            AnswerSet result = getAnswerSet();
            assert result != null;

            return getAction(result);
        } catch (Exception e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
            return "0 0 0";
        }
    }
}

