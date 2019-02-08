package org.shaheen.nazarov.server.security;

import org.shaheen.nazarov.server.util.ServerConstants;
import org.shaheen.nazarov.server.util.ServerProperty;
import org.shaheen.nazarov.server.util.ServerPropertySingleton;

public class BasicAuthenticator extends com.sun.net.httpserver.BasicAuthenticator {

    private ServerProperty serverProperty = ServerPropertySingleton.getInstance();

    public BasicAuthenticator() {
        super("lightServer");
    }

    @Override
    public boolean checkCredentials(String s, String s1) {
        if (serverProperty.getAuth()
                .equals(ServerConstants.PROPERTY_DEFAULT_AUTH_BASIC) &&
                serverProperty.getUsername().equals(s) && serverProperty.getPassword().equals(s1)
        )
            return true;
        return false;
    }
}
