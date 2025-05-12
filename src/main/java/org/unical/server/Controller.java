package org.unical.server;

import lombok.AllArgsConstructor;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unical.server.command.Command;
import org.unical.server.model.Input;
import org.unical.server.model.Output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
}
