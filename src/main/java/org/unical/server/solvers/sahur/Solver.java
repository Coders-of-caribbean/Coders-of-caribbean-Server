package org.unical.server.solvers.sahur;

import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import org.unical.server.predicates.actions.Move;
import org.unical.server.solvers.sahur.predicates.TargetFact;
import org.unical.server.solvers.sahur.program.ASPVariableProgram;

import java.util.Optional;
import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {
    private final String encodingsPath = "src/main/java/org/unical/server/solvers/sahur/program/";

    private final ASPInputProgram staticDomain = new ASPInputProgram();
    private final ASPInputProgram dynamicFacts = new ASPInputProgram();
    private final ASPInputProgram strategy = new ASPVariableProgram<>(encodingsPath + "modules/target_picker", TargetFact.class);
    private final ASPInputProgram pathfinding = new ASPVariableProgram<>(encodingsPath + "modules/path_finding", Move.class);

    private final ASPInputProgram path_finding = new ASPInputProgram();
    private final ASPInputProgram nearest_rum = new ASPInputProgram();

    static {
        try {
            ASPMapper.getInstance().registerClass(TargetFact.class);
            Logger.getAnonymousLogger().warning("Registered classes");
        } catch (Exception e) {
            throw new RuntimeException("Problems registering classes", e);
        }
    }

    public Solver(){
        super();
        fixed.addFilesPath(encodingsPath + "domain");
        handler.addProgram(fixed);
        nearest_rum.addFilesPath(encodingsPath + "modules/target_picker");
        path_finding.addFilesPath(encodingsPath + "modules/path_finding2");
    }

    private String nextMove(TargetFact targetFact, PlayerData data) throws Exception {
        try {
            addFacts(path_finding, data);
            path_finding.addObjectInput(targetFact);
            handler.addProgram(path_finding);
            AnswerSet result = getAnswerSet();
            return getAction(result);
        }finally {
            path_finding.clearPrograms();
            handler.removeProgram(path_finding);
        }
    }

    private TargetFact chooseTarget(PlayerData data) throws Exception {
        try {
            handler.removeAll();
            addFacts(nearest_rum, data);
            handler.addProgram(nearest_rum);
            AnswerSet result = getAnswerSet();
            Optional<Object> target = result.getAtoms().stream().filter((predicate) -> predicate instanceof TargetFact).findFirst();
            return (TargetFact) target.orElseThrow();
        }finally {
            nearest_rum.clearPrograms();
            handler.removeProgram(nearest_rum);
        }
    }


    @Override
    public String solve(PlayerData data) {
        try{
            current_facts.addObjectInput(chooseTarget(data));
            TargetFact target = chooseTarget(data);
            return nextMove(target, data);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "1 0 0";
    }
}

