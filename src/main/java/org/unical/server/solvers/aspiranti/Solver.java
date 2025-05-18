package org.unical.server.solvers.aspiranti;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.command.Command;
import org.unical.server.model.PlayerData;

@Component("aspiranti")
public class Solver extends AbstractSolver {

    public Solver(){
        super();
    }

    @Override
    public String solve(PlayerData input) {
        InputProgram inputProgram = new ASPInputProgram();

        try {
            handler.removeAll();
            //inputProgram.addProgram("move(1).");

            inputProgram.addFilesPath("src/main/java/org/unical/server/solvers/aspiranti/program/program");
            handler.addProgram(inputProgram);
            addFacts(inputProgram, input);

            AnswerSet result = getAnswerSet();
            //assert result != null;

            System.out.println(getAction(result));
            return getAction(result);

            /*Command c = new Command();
            c.generateRandomCommand();
            return c.getCommand();*/
        }catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "0 0 0";
        }
    }
}
