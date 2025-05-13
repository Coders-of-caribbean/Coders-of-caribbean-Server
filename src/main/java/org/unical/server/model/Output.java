package org.unical.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
//EXTENSIBLE!
public class Output {
    private Map<String, String> output;

    public Output(){
        this.output = new HashMap<>();
    }

    public void insert(String solverName, String result){
        this.output.put(solverName, result);
    }
}
