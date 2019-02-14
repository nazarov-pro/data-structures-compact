package org.shahin.nazarov.servermanagement.model;

import lombok.Data;

@Data
public class JarFile {
    private String name;
    private boolean open;
    private String modified;
}
