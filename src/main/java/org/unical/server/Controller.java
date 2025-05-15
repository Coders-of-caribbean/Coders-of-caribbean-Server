package org.unical.server;

import lombok.AllArgsConstructor;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unical.server.command.Command;
import org.unical.server.model.Input;
import org.unical.server.model.Output;

import java.util.*;

@RestController
//@RequestMapping(value = "api/v1/", consumes = "application/json")
@RequestMapping(value="api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class Controller {
    // grazie a @ComponentScan e a @Component, Spring individuerà tutti i bean e li popolerà nella lista.
    //ovviamente, viene definita nel costruttore!
    private List<ResponseSolver> solvers;

    @PostMapping(path="/solve", produces = "application/json")
    public ResponseEntity<Output> solve(@RequestBody Input input) {
        Output output = new Output();

        solvers.forEach(s -> {
            String result = s.solve(input);
            String solverName = s.getBeanName();
            output.getResult().put(solverName, result);
        });

        return ResponseEntity.ok(output);
    }

    @GetMapping(path="/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("borroto");
    }

    @GetMapping(path="/random/command")
    public Command randomCommand() {
        Command cmd = new Command();
        cmd.generateRandomCommand();
        return cmd;
    }

    @PostMapping(path="/random/command/multiple", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Map<String, Command>> randomCommandMultiple(@RequestBody(required = false) Map<String, Object> requestData) {
        // Stampa il JSON ricevuto per debug
        if (requestData != null) {
            System.out.println("Received JSON data: " + requestData);
        }

        List<Command> commands = new ArrayList<>();
        commands.add(new Command());
        commands.add(new Command());
        commands.add(new Command());
        commands.get(0).generateRandomCommand();
        commands.get(1).generateRandomCommand();
        commands.get(2).generateRandomCommand();

        // Creazione della mappa con la struttura richiesta
        Map<String, Command> response = new LinkedHashMap<>(); // LinkedHashMap mantiene l'ordine di inserimento
        response.put("thommardo", commands.get(0));
        response.put("sahur", commands.get(1));
        response.put("aspiranti", commands.get(2));

        return ResponseEntity.ok(response);
    }
}
