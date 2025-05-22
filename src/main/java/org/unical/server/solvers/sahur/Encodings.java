package org.unical.server.solvers.sahur;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Encodings {
    BASE("coords.lp");

    private final String encoding;
}
