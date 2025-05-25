package org.unical.server.solvers.sahur;

import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import org.unical.server.predicates.PlayerFact;
import org.unical.server.predicates.actions.Move;
import org.unical.server.solvers.sahur.predicates.TargetFact;
import org.unical.server.solvers.sahur.program.ASPVariableProgram;

import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {
    private final String encodingsPath = "src/main/java/org/unical/server/solvers/sahur/program/";

    private final ASPInputProgram domain = new ASPInputProgram();
    private final ASPInputProgram facts = new ASPInputProgram();

    private final ASPVariableProgram<TargetFact> strategy = new ASPVariableProgram<>(encodingsPath + "modules/target_picker", TargetFact.class);
    private final ASPVariableProgram<Move> pathfinding = new ASPVariableProgram<>(encodingsPath + "modules/path_finding", Move.class);

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
        domain.addFilesPath(encodingsPath + "modules/domain");
        handler.addProgram(domain);
        handler.addProgram(facts);
    }

    private void addFacts(PlayerData data){
        facts.clearAll();
        super.addFacts(facts, data);
    }

    @Override
    public String solve(PlayerData data) {
        try{
            addFacts(data);
            TargetFact target = strategy.compute(this);
            pathfinding.addObjectInput(target);
            Move move = pathfinding.compute(this);
            return move.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "1 0 0";
    }
}

