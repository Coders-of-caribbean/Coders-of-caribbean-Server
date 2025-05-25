package org.unical.server.solvers.aspiranti;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.PlayerData;

import java.util.Random;
import java.util.logging.Logger;


@Component("aspiranti")
public class Solver extends AbstractSolver {
    @Override
    public String solve(PlayerData playerData){
        InputProgram inputProgram = new ASPInputProgram();
        //Random rand = new Random();
        try {
            handler.removeAll();
            //String randomCommand = String.format("move(%d,%d,%d)", rand.nextInt(1,3), rand.nextInt(0,23), rand.nextInt(0,21) );
            inputProgram.addFilesPath("src/main/java/org/unical/server/solvers/aspiranti/asp");
            handler.addProgram(inputProgram);
            addFacts(inputProgram, playerData);

            AnswerSet result = getAnswerSet();
            System.out.println(result);

            assert result != null;

            return getAction(result);
        } catch (Exception e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
            e.printStackTrace();
            //return String.format("%d %d %d",rand.nextInt(1,3), rand.nextInt(0,23), rand.nextInt(0,21)  );
        }
        return "0 0 0";
    }
}