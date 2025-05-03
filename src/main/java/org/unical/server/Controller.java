package org.unical.server;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unical.server.config.SolverList;

import java.util.List;

@RestController
@RequestMapping(value = "/solve", consumes = "application/json")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class Controller {
    //it's a bean!
    private SolverList solverList;

    @PostMapping(path="/", produces = "application/json")
    public ResponseEntity<?> solve(@RequestBody Input input) {
        solverList.getSolvers().forEach(solver -> solver.solve(input));
        return ResponseEntity.ok().build();
    }
}
