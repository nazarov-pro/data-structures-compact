package org.shaheen.nazarov.server.domain;

import lombok.Data;
import org.shaheen.nazarov.server.util.ServerConstants;

import java.util.List;

@Data
public class Handshake {
    private String status;
    private String name;
    private String jarFileName;
    private String port;
    private String host;
}
