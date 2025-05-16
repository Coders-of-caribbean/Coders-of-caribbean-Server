package org.unical.server.solvers.tommardo.sol;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;
import org.unical.server.model.*;
import org.unical.server.solvers.tommardo.facts.*;

import java.util.HashSet;
import java.util.Set;

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
            program = addFacts(program, data);
            handler.addProgram(program);

            //2. variable program (strategy)
            InputProgram strat = new ASPInputProgram();
            strat.addFilesPath("src/main/java/org/unical/server/solvers/tommardo/encodings/searchForRumStrat");
            handler.addProgram(strat);

            /*if we want more implementations:*/
            //strat.clearAll(); //and add other strategies!

            //4. generate answer set (just one since there is just a solution.)
            AnswerSet result = getAnswerSet();

            for(Object atom: result.getAtoms()){
                if(! (atom instanceof SolutionFact))
                    continue;

                System.out.println("eccolo!");
                SolutionFact sol = (SolutionFact) atom;

                //given the solution, return the current string.
                System.out.println(beanName + " " + sol.getX() + " "+ sol.getY()+" "+sol.getSpeed());
                return sol.getX() + " "+ sol.getY()+" "+sol.getSpeed();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
