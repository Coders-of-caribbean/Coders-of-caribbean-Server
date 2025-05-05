package org.unical.server;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@SpringBootApplication
//scansiono i Bean a partire da org.unical.server.solvers: I BEAN DOVRANNO ESSERE QUI DENTRO!
@ComponentScan(basePackages = "org.unical.server.solvers")
public class ServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerApplication.class, args);

        Map<String, Solvable> solvers = context.getBeansOfType(Solvable.class);

        System.out.println("=== Solvers registrati ===");
        solvers.forEach((name, bean) -> {
            System.out.println("Bean name: " + name + " | Classe: " + bean.getClass().getName());
        });
    }

}
