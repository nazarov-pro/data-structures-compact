package org.shaheen.nazarov.server.security;

import com.sun.net.httpserver.Authenticator;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shaheen.nazarov.server.util.ServerProperty;
import org.shaheen.nazarov.server.util.ServerPropertySingleton;

public class CerificateAuthenticator extends Authenticator {

    private ServerProperty serverProperty = ServerPropertySingleton.getInstance();

    private String realm;

    public CerificateAuthenticator() {
        this.realm = "LightServer";
    }


    @Override
    public Result authenticate(HttpExchange httpExchange) {
        Headers var2 = httpExchange.getRequestHeaders();
        String var3 = var2.getFirst(ServerConstants.HEADER_KEY_AUTHORIZATION);
        if (var3 == null) {
            Headers var11 = httpExchange.getResponseHeaders();
            var11.set("WWW-Authenticate", "Signature realm 5rvqtcl=\"" + this.realm + "\"");
            return new Retry(401);
        } else {
            if (var3.startsWith(ServerConstants.AUTH_CERTIFICATE_PREFIX)) {
                String hash = var3.substring(ServerConstants.AUTH_CERTIFICATE_PREFIX.length());

                if (serverProperty.getAuth().equals(ServerConstants.PROPERTY_DEFAULT_AUTH_CERIFICATE) &&
                        hash.equals("")) {
                    return new Success(new HttpPrincipal("user", this.realm));
                } else {
                    Headers var10 = httpExchange.getResponseHeaders();
                    var10.set("WWW-Authenticate", "Signature realm=\"" + this.realm + "\"");
                    return new Failure(401);
                }
            } else {
                return new Failure(401);
            }
        }
    }

}