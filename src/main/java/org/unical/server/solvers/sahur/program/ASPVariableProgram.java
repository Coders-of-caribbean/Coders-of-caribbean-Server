package org.unical.server.solvers.sahur.program;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import org.unical.server.AbstractSolver;

public class ASPVariableProgram<T> extends ASPInputProgram {
    private final Class<T> output;

    public ASPVariableProgram(String filePath, Class<T> output) {
        this.addFilesPath(filePath);
        this.output = output;
    }

    public final T compute(AbstractSolver solver) {
        Handler handler = solver.getHandler();
        try {
            handler.addProgram(this);
            System.out.println("Atoms: " + solver.getAnswerSet()); // stampa cruda
            T result =  solver.getAnswerSet().getAtoms()
                    .stream()
                    .filter(output::isInstance)
                    .map(output::cast)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No matching atom of type " + output.getSimpleName()));
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            handler.removeProgram(this);
            this.clearPrograms();
        }
    }
}
