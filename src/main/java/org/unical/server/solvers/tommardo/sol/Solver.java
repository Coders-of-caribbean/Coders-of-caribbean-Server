package org.unical.server.solvers.tommardo.sol;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import org.springframework.stereotype.Component;
import org.unical.server.ResponseSolver;
import org.unical.server.model.*;
import org.unical.server.solvers.tommardo.facts.*;

import java.util.HashSet;
import java.util.Set;

@Component
public class Solver extends ResponseSolver {

    private Handler handler;

    private SolutionFact oldSol;
    private SolutionFact newSol;

    @Override
    public String solve(Input input) throws Exception {
        DesktopService service = new DLV2DesktopService ("lib/dlv2.exe");

        handler = new DesktopHandler(service);

        OptionDescriptor option = new OptionDescriptor("--no-facts");
        handler.addOption(option);

        //fixed program (facts)
        addFacts(input);

        //variable program (strat)
        InputProgram strat = new ASPInputProgram();
        strat.addFilesPath("src/main/java/org/unical/server/solvers/tommardo/encodings/searchForRumStrat");
        handler.addProgram(strat);

        //here I generate the output!
        Output output = handler.startSync();

        /*if we want more implementations:*/
        //strat.clearAll(); //and add other strategies!

        AnswerSets answerSets = (AnswerSets) output;

        //get optimal answer sets
        for(AnswerSet a : answerSets.getOptimalAnswerSets()){
            for(Object atom: a.getAtoms()){
                if(! (atom instanceof SolutionFact))
                    continue;

                if(newSol != null)
                    oldSol = newSol;
                newSol = (SolutionFact) atom;
            }
        }
        return computeAction();
    }

    private void addFacts(Input input) throws Exception {
        Set<Object> facts = new HashSet<>();
        InputProgram factsProgram = new ASPInputProgram();

        for(Barrel b : input.getBarrels())
            facts.add(new BarrelFact(b.x,b.y));

        for(Ship s : input.getShips()){
            if(s.getName().equals(beanName))
                facts.add(new PlayerFact(s.x,s.y,s.getRum()));
            else
                facts.add(new EnemyFact(s.x,s.y,s.getRum()));
        }

        for(Bomb b : input.getBombs())
            facts.add(new BombFact(b.x,b.y));

        factsProgram.addObjectsInput(facts);

        handler.addProgram(factsProgram);
    }

    private String computeAction(){
        if(oldSol == null)
            return "MOVE " + newSol.getX() + " " + newSol.getY() + " " + newSol.getSpeed();

        if(oldSol.getX() != newSol.getX() || oldSol.getY() != newSol.getY())
            return "MOVE " + newSol.getX() + " " + newSol.getY() + " " + newSol.getSpeed();

        if(oldSol.getSpeed() != newSol.getSpeed())
            return "SLOWER";

        return "WAIT";
    }
}
