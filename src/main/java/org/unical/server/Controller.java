package org.unical.server;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/solve", consumes = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class Controller {

    // grazie a @ComponentScan e a @Component, Spring individuerà tutti i bean e li popolerà nella lista.
    //ovviamente, viene definita nel costruttore!
    private List<Solvable> solvers;

    @PostMapping(path="/", produces = "application/json")
    public ResponseEntity<?> solve(@RequestBody Input input) {
        solvers.stream().forEach(s -> s.solve(input));
        //TODO costruire la stringa e restituirla al game.
        return ResponseEntity.ok().build();
    }
}
