package org.unical.server.solvers.sahur;

import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import org.unical.server.predicates.actions.Move;
import org.unical.server.solvers.sahur.predicates.TargetFact;
import org.unical.server.solvers.sahur.program.ASPVariableProgram;

import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {
    private final String encodingsPath = "src/main/java/org/unical/server/solvers/sahur/program/";

    private ASPInputProgram domain;
    private ASPInputProgram facts;
    private ASPVariableProgram<TargetFact> strategy;
    private ASPVariableProgram<Move> pathfinding;


    static {
        try {
            ASPMapper.getInstance().registerClass(TargetFact.class);
            Logger.getAnonymousLogger().warning("Registered classes");
        } catch (Exception e) {
            throw new RuntimeException("Problems registering classes", e);
        }
    }

    public Solver() {
        super();
        strategy = new ASPVariableProgram<>(encodingsPath + "modules/target_picker", TargetFact.class);
        pathfinding =  new ASPVariableProgram<>(encodingsPath + "modules/path_finding", Move.class);

        domain = new ASPInputProgram(encodingsPath + "modules/domain");
        facts = new ASPInputProgram();

        handler.addProgram(domain);
        handler.addProgram(facts);
    }

    private void addFacts(PlayerData data){
        facts.clearPrograms();
        super.addFacts(facts, data);
    }

    @Override
    public String solve(PlayerData data) {
        try{
            addFacts(data);
            strategy.addProgram(facts.getPrograms());
            TargetFact target = strategy.compute(this);
            //pathfinding.addObjectInput(target);
            //Move move = pathfinding.compute(this);
            //return move.toString();
            return null;
        }catch(Exception e){
            e.printStackTrace();
        }
        return "1 0 0";
    }
}

