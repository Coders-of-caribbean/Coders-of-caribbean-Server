package org.unical.server.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
//EXTENSIBLE!
public class Output {
    private Map<String, String> result;
}
