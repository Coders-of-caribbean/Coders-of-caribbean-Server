package org.unical.server.solvers.tommardo;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.*;

@Component("thommardo")
public class Solver extends AbstractSolver {

    public Solver(){
        super();
    }

    @Override
    public String solve(PlayerData data) {
        try{
            //1. fixed problem (facts)
            InputProgram program = new ASPInputProgram();
            handler.addProgram(program);
            addFacts(program, data);

            //2. variable program (strategy)
            InputProgram strat = new ASPInputProgram();
            strat.addFilesPath("src/main/java/org/unical/server/solvers/tommardo/encodings/searchForRumStrat");
            handler.addProgram(strat);

            /*NOTE if we want more implementations:*/
            //strat.clearAll(); //and add other strategies into the handler!

            //3. generate answer set (just one since there is just a solution.)
            AnswerSet result = getAnswerSet();

            //4. get the consequent action
            return getAction(result);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
