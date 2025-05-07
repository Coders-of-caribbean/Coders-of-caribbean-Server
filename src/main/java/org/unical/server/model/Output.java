package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
//EXTENSIBLE!
public class Output {
    private Map<String, String> result;

    public Output() {
        result = new HashMap<>();
    }
}
