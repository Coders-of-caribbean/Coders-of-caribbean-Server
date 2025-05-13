package org.unical.server;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.BeanNameAware;
import org.unical.server.model.Input;

import java.io.File;

/*
**** SOSTITUITA ALL'INTERFACCIA SOLVABLE ****
* Implemento BeanNameAware, interfaccia che permette di spefificare all'interno dell'oggetto
* il nome del bean.
* In questo modo, posso accedervi facilmente attraverso un banale getter.
* NOTA: non è necessario specificare nulla lato solver:
* Cambiare il nome del bean @Component("nuovoNomeBean") fa sì che il nome di esso venga automaticamente memorizzato al suo interno.
*
**** PERCHE' SERVE? TUTTO QUESTO? ****
* In realtà basterebbe la sola interfaccia Solvable se i nomi delle classi solver fossero tutti diversi.
*
* Considera i due solver presenti nel package solvers: essi hanno lo stesso nome e sono configurati
* attraverso l'annotazione @Component ad essere bean.
* DUE CLASSI BEAN AVENTI LO STESSO NOME CAUSA IL THROW DI UN'ECCEZIONE (ConflictingBeanDefinitionException).
*
* SOLUZIONI:
* 1. puoi mantenere lo stesso nome della classe e definire un nome del bean differente specificandolo come parametro di @Component
* 2. cambiare il nome della classe.
*
* Se si adotta la prima soluzione e si cambia il nome del bean specificandolo come parametro di @Component:
* @Component("nuovoNomeBean")
*
* allora si, è NECESSARIO.
* */

@Getter
@Setter
public abstract class Solver implements BeanNameAware {
    private Handler handler;
    private String beanName;

    public Solver() {
        String binary = getBinary();
        handler = new DesktopHandler(new DLV2DesktopService("lib/" + binary));
    }
    private String getBinary(){
        String system = System.getProperty("os.name");
        if(system.startsWith("Windows")) return "dlv2.exe";
        if(system.startsWith("Mac")) return "dlv2.bin";
        if(system.startsWith("Linux")) return "dlv2.bin";
        throw new RuntimeException("Unsupported system: " + system);
    }

    public abstract String solve(Input input);
}
