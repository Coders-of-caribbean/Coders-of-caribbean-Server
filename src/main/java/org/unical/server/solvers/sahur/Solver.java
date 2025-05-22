package org.unical.server.solvers.sahur;


import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.PlayerData;
import org.unical.server.solvers.sahur.predicates.TargetFact;

import java.util.Map;
import java.util.Random;
import java.util.logging.Logger;

@Component("sahur")
public class Solver extends AbstractSolver {
    private final String encodingsPath = "src/main/java/org/unical/server/solvers/sahur/program/";

    private static ASPInputProgram strategy;
    private static ASPInputProgram base_program;
    private static ASPInputProgram path_finding;
    private static ASPInputProgram nearest_rum;

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
        base_program = new ASPInputProgram();
        path_finding = new ASPInputProgram();
        nearest_rum = new ASPInputProgram();
        nearest_rum.addFilesPath(encodingsPath + "find_rum");
        path_finding.addFilesPath(encodingsPath + "path_finding");
        strategy = new ASPInputProgram();
    }

    private TargetFact getTarget(AnswerSet answerSet) {
        try {
            return (TargetFact) answerSet.getAtoms().stream().filter((predicate) -> predicate instanceof TargetFact).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String solve(PlayerData data) {
        try{
            handler.removeAll();
            addFacts(nearest_rum, data);
            handler.addProgram(nearest_rum);
            AnswerSet result = getAnswerSet();
            TargetFact target = getTarget(result);

            handler.removeAll();
            handler.removeProgram(nearest_rum);
            addFacts(path_finding, data);
            path_finding.addObjectInput(target);
            handler.addProgram(path_finding);
            result = getAnswerSet();
            handler.removeProgram(path_finding);

            return getAction(result);

        }catch(Exception e){
            e.printStackTrace();
        }
        return "0 0 0";
    }

    private void addStrategy(PlayerData data){
        handler.removeAll();
        InputProgram strat = new ASPInputProgram();
        strat.addFilesPath(encodingsPath + "program");
        handler.addProgram(strat);
        addFacts(strat, data);
    }
}

