package org.shaheen.nazarov.server.domain;

import lombok.Data;

@Data
public class ServerStatus {
    private String name;
    private String port;
    private String host;
    private String status;
}
