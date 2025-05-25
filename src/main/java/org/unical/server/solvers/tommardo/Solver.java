package org.unical.server.solvers.tommardo;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.*;

@Component("thommardo")
public class Solver extends AbstractSolver {

    private final String encodingsPath = "src/main/java/org/unical/server/solvers/tommardo/encodings/";

    public Solver(){
        super();
    }

    @Override
    public String solve(PlayerData data) {
        try{
            System.out.println(data.getBarrels());
            System.out.println(data.getMines());

            for(Mine m : data.getMines()){
                System.out.println("\n");
                System.out.println(m.x);
                System.out.println(m.y);
            }

            System.out.println(data.getPlayerInfo().getDirection());
            handler.removeAll();
            //1. fixed problem (facts)
            InputProgram program = new ASPInputProgram();
            handler.addProgram(program);
            addFacts(program, data);

            //2. variable program (strategy)
            addStrategy(data);

            /*NOTE if we want more implementations:*/
            //strat.clearAll(); //and add other strategies into the handler!

            //3. generate answer set (just one since there is just a solution.)
            AnswerSet result = getAnswerSet();

            System.out.println(result.toString());
            //4. get the consequent action
            return getAction(result);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    private void addStrategy(PlayerData data){
        InputProgram strat = new ASPInputProgram();
        if(data.getBarrels().size() == 0)
            strat.addFilesPath(encodingsPath + "hunt");
        else if(data.getPlayerInfo().getRum() <= 80) {
            strat.addFilesPath(encodingsPath + "survive");
       } else{
            strat.addFilesPath(encodingsPath + "patrol");
        }

        handler.addProgram(strat);
    }
}
