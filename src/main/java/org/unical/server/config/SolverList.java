package org.unical.server.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.unical.server.Solvable;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class SolverList {

    private final List<Solvable> solvers;

    public SolverList(String solversPath) {
        solvers = loadSolvers(solversPath);
    }

    private List<Solvable> loadSolvers(String baseDirPath) {
        List<Solvable> solvers = new ArrayList<>();

        File baseDir = new File(baseDirPath);

        try {
            URL[] urls = { baseDir.toURI().toURL() };
            URLClassLoader classLoader = new URLClassLoader(urls);
            System.out.println(baseDirPath);

            List<String> classNames = findClassNames(baseDir, baseDir);

            for (String className : classNames) {
                try {
                    System.out.println(baseDirPath);
                    System.out.println(className);
                    Class<?> cls = classLoader.loadClass(className);
                    if (Solvable.class.isAssignableFrom(cls)) {
                        Solvable solver = (Solvable) cls.getDeclaredConstructor().newInstance();
                        solvers.add(solver);
                    }
                } catch (Exception e) {
                    System.err.println("Errore caricando la classe: " + className);
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return solvers;
    }

    //double File used for recursion
    private static List<String> findClassNames(File baseDir, File currentDir) {
        List<String> classNames = new ArrayList<>();
        File[] files = currentDir.listFiles();

        if(files == null) return classNames;

        for (File file : files) {
            if (file.isDirectory()) {
                classNames.addAll(findClassNames(baseDir, file));
            } else if (file.getName().endsWith(".class")) {
                //trasformo l'URI del base file (percorso assoluto) in un percorso relativo fino all'uri del file specifico.
                String relativePath = baseDir.toURI().relativize(file.toURI()).getPath();

                //ottengo il nome della classe rimpiazzando / con . e rimuovendo l'estensione .class
                String className = relativePath.replace(File.separator, ".").replace(".class", "");
                classNames.add(className);
            }
        }
        return classNames;
    }
}
