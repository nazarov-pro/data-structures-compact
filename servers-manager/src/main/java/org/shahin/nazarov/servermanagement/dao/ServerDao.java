package org.shahin.nazarov.servermanagement.dao;

import org.shahin.nazarov.servermanagement.model.Server;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class ServerDao {
    private static Set<Server> servers = new HashSet<>();

    public Optional<Server> get(String fileName) {
        return servers.stream().filter(server -> server.getFileName().equals(fileName)).findFirst();
    }

    public Stream<Server> list() {
        return servers.stream();
    }

    public boolean add(Server server) {
        return servers.add(server);
    }

    public boolean update(Server server, String fileName) {
        if (delete(fileName))
            return add(server);
        return false;
    }

    public boolean delete(String fileName) {
        Optional<Server> server = get(fileName);
        if (server.isPresent()) {
            servers.remove(server.get());
            return true;
        }
        return false;
    }

}
