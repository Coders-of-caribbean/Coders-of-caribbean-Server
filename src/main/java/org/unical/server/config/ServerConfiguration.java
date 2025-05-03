package org.unical.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class ServerConfiguration {

    private static final String path = "target"+ File.separator+"classes"+ File.separator+"org"+ File.separator+
            "unical"+ File.separator+"server"+ File.separator+"solvers"+ File.separator;

    //here I create the SolverList bean
    @Bean
    public SolverList solverList() {
        return new SolverList(path);
    }
}
