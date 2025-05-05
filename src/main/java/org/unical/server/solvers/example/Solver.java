package org.unical.server.solvers.example;

import org.springframework.stereotype.Component;
import org.unical.server.Input;
import org.unical.server.Solvable;

//NOTA: classe usata a solo scopo di esempio.
//nel package tommardo.sol è presente una classe avente lo stesso nome.
//DUE CLASSI BEAN AVENTI LO STESSO NOME CAUSA IL THROW DI UN'ECCEZIONE (ConflictingBeanDefinitionException).
//
//SOLUZIONE:
//puoi mantenere lo stesso nome della classe e definire un nome del bean differente specificandolo come parametro di @Component
//cambiare il nome della classe.
@Component("otherSolver")
public class Solver implements Solvable {
    @Override
    public String solve(Input input) {
        return "";
    }
}
