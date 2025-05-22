package org.unical.server;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.unical.server.model.PlayerData;
import org.unical.server.predicates.EnemyFact;
import org.unical.server.predicates.PlayerFact;
import org.unical.server.predicates.actions.Action;
import org.unical.server.predicates.actions.Move;
import org.unical.server.predicates.objects.MineFact;
import org.unical.server.predicates.objects.BarrelFact;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
**** SOSTITUITA ALL'INTERFACCIA SOLVABLE ****
* Implemento BeanNameAware, interfaccia che permette di spefificare all'interno dell'oggetto
* il nome del bean.
* In questo modo, posso accedervi facilmente attraverso un banale getter.
* NOTA: non è necessario specificare nulla lato solver:
* Cambiare il nome del bean @Component("nuovoNomeBean") fa sì che il nome di esso venga automaticamente memorizzato al suo interno.
*
**** PERCHE' SERVE? TUTTO QUESTO? ****
* In realtà basterebbe la sola interfaccia Solvable se i nomi delle classi solver fossero tutti diversi.
*
* Considera i due solver presenti nel package solvers: essi hanno lo stesso nome e sono configurati
* attraverso l'annotazione @Component ad essere bean.
* DUE CLASSI BEAN AVENTI LO STESSO NOME CAUSA IL THROW DI UN'ECCEZIONE (ConflictingBeanDefinitionException).
*
* SOLUZIONI:
* 1. puoi mantenere lo stesso nome della classe e definire un nome del bean differente specificandolo come parametro di @Component
* 2. cambiare il nome della classe.
*
* Se si adotta la prima soluzione e si cambia il nome del bean specificandolo come parametro di @Component:
* @Component("nuovoNomeBean")
*
* allora si, è NECESSARIO.
* */

@Getter
@Setter
public abstract class AbstractSolver implements BeanNameAware {
    protected Handler handler;
    @Getter
    private String beanName;

    private static final Class<?>[] classes = {Move.class, MineFact.class, BarrelFact.class, PlayerFact.class, EnemyFact.class};


    static {
        try {
            for (Class<?> clazz : classes) {
                ASPMapper.getInstance().registerClass(clazz);
            }
            Logger.getAnonymousLogger().warning("Registered classes");
        } catch (Exception e) {
            throw new RuntimeException("Problems registering classes", e);
        }
    }

    public AbstractSolver() {
        String binary = getBinary();
        handler = new DesktopHandler(new DLV2DesktopService("lib/" + binary));
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
    protected String getAction(AnswerSet answerSet){
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

    /**
     * A volte potrebbe succedere che il programma decida di non usare weak constraint
     * questa funzione serve per ottenere un {@link AnswerSet} ottimale se esiste, uno generico altrimenti.
     *
     * @return un {@link AnswerSet} ottimo se esiste, un generico altrimenti.
     * @throws {@link RuntimeException} se INCOHERENT
     * @throws {@link NoSuchElementException} se non è presente nessun {@link AnswerSet}
     */
    protected AnswerSet getAnswerSet() {
        AnswerSets answerSets = (AnswerSets) handler.startSync();

        if(answerSets.getOutput().contains("INCOHERENT"))
            throw new RuntimeException("INCOHERENT ANSWER SET");

        if(!answerSets.getOptimalAnswerSets().isEmpty())
            return answerSets.getOptimalAnswerSets().getFirst();

        if(!answerSets.getAnswersets().isEmpty())
            return answerSets.getAnswersets().getFirst();

        throw new NoSuchElementException();
    }

    /**
     * Questa funzione è un utility che serve per aggiungere ad un {@link InputProgram},
     * i fatti che sono presenti in {@link PlayerData}.
     *
     * @param program Program to add the facts
     * @param input PlayerData
     * @return {@link InputProgram} passato come parametro, oppure {@code null} se l'operazione non va a buon fine.
     */
    protected InputProgram addFacts(InputProgram program, PlayerData input){
        assert input != null;
        assert program != null;

        try {
            Set<Object> rum = input.getBarrels()
                    .stream()
                    .map((BarrelFact::new))
                    .collect(Collectors.toSet());

            Set<Object> enemies = input.getEnemiesInfo()
                    .stream()
                    .map((EnemyFact::new))
                    .collect(Collectors.toSet());

            Set<Object> bombs = input.getMines()
                    .stream()
                    .map((MineFact::new))
                    .collect(Collectors.toSet());

            PlayerFact playerFact = new PlayerFact(input.getPlayerInfo());

            program.addObjectsInput(bombs);
            program.addObjectsInput(enemies);
            program.addObjectsInput(rum);
            program.addObjectInput(playerFact);
            return program;
        }catch (Exception e){
            return null;
        }
    }

    private String getBinary(){
        String system = System.getProperty("os.name");
        if(system.startsWith("Windows")) return "dlv2.exe";
        if(system.startsWith("Linux")) return "binario_x_massimo";

        if(system.startsWith("Mac")){
            String arch = System.getProperty("os.arch");
            if(arch.contains("aarch64") || arch.contains("arm64")) return "dlv2.mac_5";
            return "dlv2-intel.mac";
        }

        throw new RuntimeException("Unsupported system: " + system);
    }

    public String solve(PlayerData playerData){
        InputProgram inputProgram = new ASPInputProgram();
        Random rand = new Random();
        try {
            handler.removeAll();
            String randomCommand = String.format("move(%d,%d,%d)", rand.nextInt(1,3), rand.nextInt(0,23), rand.nextInt(0,21) );
            inputProgram.addProgram(randomCommand);
            handler.addProgram(inputProgram);
            addFacts(inputProgram, playerData);

            AnswerSet result = getAnswerSet();
            assert result != null;

            return getAction(result);
        } catch (Exception e) {
            Logger.getAnonymousLogger().warning(e.getMessage());
            return String.format("%d %d %d",rand.nextInt(1,3), rand.nextInt(0,23), rand.nextInt(0,21)  );
        }
    }
}
