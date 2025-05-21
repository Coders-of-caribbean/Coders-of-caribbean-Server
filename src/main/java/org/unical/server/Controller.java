package org.unical.server;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.unical.server.command.Command;
import org.unical.server.model.Input;
import org.unical.server.model.Output;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

//TODO realize an ExceptionHandler class

@RestController
//@RequestMapping(value = "api/v1/", consumes = "application/json")
@RequestMapping(value="api/v1/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class Controller {
    // grazie a @ComponentScan e a @Component, Spring individuerà tutti i bean e li popolerà nella lista.
    //ovviamente, viene definita nel costruttore!
    private List<AbstractSolver> solvers;
    private ExecutorService executor;

    @PostMapping(path="/solve", produces = "application/json")
    public ResponseEntity<Output> solve(@RequestBody Input input) {
        Output output = new Output();


        List<CompletableFuture<Void>> futures = solvers.stream()
                .filter(s -> input.getInput().containsKey(s.getBeanName()))
                .map(s -> CompletableFuture.runAsync(()->{
                    try{
                        String result = s.solve(input.getInput().get(s.getBeanName()));
                        System.out.println("done");
                        //sezione critica: inserisco il risultato in maniera safe
                        synchronized (output) {
                            output.insert(s.getBeanName(), result);
                        }
                    }catch(Exception ex){
                        synchronized (output) {
                            //output.insert(s.getBeanName(), "!!! NO GAME INFO !!!");
                            ex.printStackTrace();
                        }
                    }
                    },executor))
                .toList();
        /*
        solvers.forEach(s -> {
            String result;
            try {
                //assign just his player data (if present!)
                if(input.getInput().containsKey(s.getBeanName())){
                    executor.submit(() -> {
                        s.solve(input.getInput().get(s.getBeanName()));
                    });
                }
                    result = s.solve(input.getInput().get(s.getBeanName()));
                else
                    //FIXME should launch an exception
                    result = "!!! NO GAME INFO !!!";
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            String solverName = s.getBeanName();
            output.insert(solverName, result);
        });
        */

        //Join
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
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
