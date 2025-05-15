package org.unical.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Map;

@SpringBootApplication
public class ServerApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerApplication.class, args);

        Map<String, ResponseSolver> solvers = context.getBeansOfType(ResponseSolver.class);

        System.out.println("=== Solvers registrati ===");
        solvers.forEach((name, bean) -> {
            System.out.println("Bean name: " + name + " | Classe: " + bean.getClass().getName());
        });
    }

}
