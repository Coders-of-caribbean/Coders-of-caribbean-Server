package org.unical.server.solvers.aspiranti;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.PlayerData;

@Component("aspiranti")
public class Solver extends AbstractSolver {


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
        }catch (Exception e) {
            return "0 0 0";
        }
    }
}
