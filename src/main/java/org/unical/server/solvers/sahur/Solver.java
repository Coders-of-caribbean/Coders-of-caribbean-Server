package org.unical.server.solvers.sahur;


import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import org.springframework.stereotype.Component;
import org.unical.server.AbstractSolver;

import org.unical.server.model.Input;
import org.unical.server.solvers.sahur.predicates.Action;
import org.unical.server.solvers.sahur.predicates.Move;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Component
public class Solver extends AbstractSolver {
    private final Class<?>[] classes = {Move.class};

    public Solver() {
        super();
        registerClasses();
    }

    private void registerClasses() {
        assert classes.length > 0 : "Com'è possibile che non hai nessuna classe da registrare?";

        try {
            for (Class<?> clazz : classes) {
                ASPMapper.getInstance().registerClass(clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException("Problems registering classes", e);
        }
    }

    /**
     * A volte potrebbe succedere che il programma decida di non usare weak constraint
     * questa funzione serve per ottenere un {@link AnswerSet} ottimale se esiste, uno generico altrimenti.
     *
     * @return un {@link AnswerSet} ottimo se esiste, un generico altrimenti.
     */

    private AnswerSet getAnswerSet() {
        AnswerSets answerSets = (AnswerSets) handler.startSync();
        try{
            return answerSets.getOptimalAnswerSets().getFirst();
        } catch (NoSuchElementException e){
            return answerSets.getAnswersets().getFirst();
        }
    }

    /**
     * Trova i predicati azione e ne restituisce uno solo.
     * Sfrutta il fatto che le azioni implementano un'interfaccia comune che impone
     * la definizione di {@link Object#toString()}.
     *
     * Nota: già a priori non dovrebbero esserci più azioni. (asp-enforced)
     *
     * @param answerSet
     * @return la stringa che rappresenta il comando.
     */
    private String getAction(AnswerSet answerSet){
        try {
            String[] actions =  answerSet.getAtoms()
                    .stream()
                    .filter((predicate) -> predicate instanceof Action)
                    .map(Object::toString)
                    .toArray(String[]::new);

            // assert actions.length == 1 : "Non dovrebbe esserci più di una azione :(";
            return actions[0];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String solve(Input input) {
        InputProgram inputProgram = new ASPInputProgram();
        try {
            handler.removeAll();
            inputProgram.addProgram("move(1..5,1..5,1).");
            handler.addProgram(inputProgram);

            AnswerSet result = getAnswerSet();
            assert result != null;

            return getAction(result);
        } catch (Exception e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
            return "move(1,1)";
        }
    }
}

