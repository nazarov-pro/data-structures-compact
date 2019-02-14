package org.shaheen.nazarov.server.util;

import lombok.Data;

@Data
public class ServerProperty {
    private String name;
    private String threadPoolSize;
    private String port;
    private String host;
    private String auth;
    private String path;
    private String username;
    private String password;
    private String token;
    private String certificate;
    private String serverManagementEndpoint;
    private String jarFileName;
    private String healthKey;

}
