package org.unical.server.solvers.tommardo.sol;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.OptionDescriptor;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.platforms.desktop.DesktopService;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import org.springframework.stereotype.Component;
import org.unical.server.ResponseSolver;
import org.unical.server.model.*;
import org.unical.server.solvers.tommardo.facts.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Component("thommardo")
public class Solver extends ResponseSolver {

    public Solver(){
        super();
    }

    @Override
    public String solve(Input input) {
        try{
            //1. class registration
            registerClasses();

            //2. fixed program (facts)
            addFacts(input);

            //3. variable program (strat)
            InputProgram strat = new ASPInputProgram();
            strat.addFilesPath("src/main/java/org/unical/server/solvers/tommardo/encodings/searchForRumStrat");
            handler.addProgram(strat);

            /*if we want more implementations:*/
            //strat.clearAll(); //and add other strategies!

            //4. generate answer set (just one since there is just a solution.)
            AnswerSets answerSets = (AnswerSets) handler.startSync();
            AnswerSet answerSet = answerSets.getAnswersets().getFirst();

            for(Object atom: answerSet.getAtoms()){
                if(! (atom instanceof SolutionFact))
                    continue;

                System.out.println("eccolo!");
                SolutionFact sol = (SolutionFact) atom;

                //return computeAction(sol, input.getShips().get(input.getShips().);
            }

            //5.given the solution, return the correct string.
        }catch(Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public void registerClasses() throws ObjectNotValidException, IllegalAnnotationException {
        ASPMapper instance = ASPMapper.getInstance();

        instance.registerClass(BarrelFact.class);
        instance.registerClass(BombFact.class);
        instance.registerClass(EnemyFact.class);
        instance.registerClass(PlayerFact.class);
        instance.registerClass(SolutionFact.class);
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

    private String computeAction(SolutionFact newSol, Ship oldSol){
        return "MOVE " + newSol.getX() + " " + newSol.getY() + " " + newSol.getSpeed();
    }
}
