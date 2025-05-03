package org.unical.server;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.unical.server.config.SolverList;

import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServerApplication.class, args);

        SolverList solverList = (SolverList) context.getBean("solverList");
        solverList.getSolvers().forEach(System.out::println);
    }

}
