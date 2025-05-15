package org.unical.server.solvers.sahur;


import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {
    //private final Class<?>[] classes = {Move.class};

    public Solver() {
        super();
        //registerClasses();
    }

    /*
    private void registerClasses() {
        assert classes.length > 0 : "Com'è possibile che non hai nessuna classe da registrare?";

        try {
            for (Class<?> clazz : classes) {
                ASPMapper.getInstance().registerClass(clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException("Problems registering classes", e);
        }
    }

     */

    @Override
    public String solve(PlayerData input) {
        InputProgram inputProgram = new ASPInputProgram();
        try {
            handler.removeAll();
            inputProgram.addProgram("move(1..5,1..5,1).");
            handler.addProgram(inputProgram);
            addFacts(inputProgram, input);

            AnswerSet result = getAnswerSet();
            assert result != null;

            return getAction(result);
        } catch (Exception e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
            return "move(1,1)";
        }
    }
}

