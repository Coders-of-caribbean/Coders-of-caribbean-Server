package org.unical.server;

import lombok.AllArgsConstructor;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unical.server.model.Input;
import org.unical.server.model.Output;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/", consumes = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class Controller {
    // grazie a @ComponentScan e a @Component, Spring individuerà tutti i bean e li popolerà nella lista.
    //ovviamente, viene definita nel costruttore!
    private List<Solvable> solvers;

    //applicationContext è quell'oggetto che memorizza e gestisce tutti i bean.
    private ApplicationContext applicationContext;

    @PostMapping(path="/solve", produces = "application/json")
    public ResponseEntity<Output> solve(@RequestBody Input input) {
        Output output = new Output();

        solvers.forEach(s -> {
            String result = s.solve(input);
            String solverName = (String)applicationContext.getBean(s.getClass().getName());
            output.getResult().put(solverName, result);
        });

        return ResponseEntity.ok(output);
    }
}
