package org.unical.server;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Path;
import java.nio.file.Paths;
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
