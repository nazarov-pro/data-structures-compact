package org.shahin.nazarov.servermanagement.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Server {
    private String label;
    private String fileName;
    private boolean active;
    private String host;
    private String port;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equals(fileName, server.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName);
    }
}
