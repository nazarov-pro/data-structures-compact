package org.shaheen.nazarov.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.shaheen.nazarov.server.util.ServerConstants;
import org.shaheen.nazarov.server.util.ServerProperty;
import org.shaheen.nazarov.server.util.ServerPropertySingleton;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Base64;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public abstract class AbstractSecuredHandler implements HttpHandler {

    private ServerProperty serverProperty = ServerPropertySingleton.getInstance();
    private String requiredHeader = null;

    protected AbstractSecuredHandler() {
        switch (serverProperty.getAuth()) {
            case ServerConstants.PROPERTY_DEFAULT_AUTH_BASIC:
                requiredHeader = ServerConstants.AUTH_BASIC_PREFIX +
                        Base64.getEncoder().encodeToString(
                                (serverProperty.getUsername() + ":" + serverProperty.getPassword()).getBytes());
                break;

            case ServerConstants.PROPERTY_DEFAULT_AUTH_TOKEN:
                requiredHeader = ServerConstants.AUTH_TOKEN_PREFIX + serverProperty.getToken();
                break;

            case ServerConstants.PROPERTY_DEFAULT_AUTH_CERIFICATE:
                requiredHeader = ServerConstants.AUTH_CERTIFICATE_PREFIX + "%s";
                break;

        }
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (requiredHeader == null ||
                serverProperty.getPath() == null ||
                serverProperty.getPath().isEmpty()) {
            successHandler(httpExchange);
            return;
        }

        String requestURI = httpExchange.getRequestURI().toString();

        if (!requestURI.matches(serverProperty.getPath())) {
            successHandler(httpExchange);
            return;
        }


        String authorization = httpExchange.getRequestHeaders().getFirst(HEADER_KEY_AUTHORIZATION);
        // TODO: Certification AES, DES, SHA WITH PASSWORD, RSA, ECC
        if (requiredHeader.endsWith("%s")) {
            String body = convertStreamToString(httpExchange.getRequestBody());
            if(!String.format(requiredHeader, body).equals(authorization)){
                errorHandler(httpExchange);
                return;
            }
        } else if (authorization == null || !authorization.equals(requiredHeader)) {
            errorHandler(httpExchange);
            return;
        }

        successHandler(httpExchange);
    }

    public void errorHandler(HttpExchange httpExchange) throws IOException {
        URI requestURI = httpExchange.getRequestURI();
        String response = String.format(FORBIDDEN_TEXT, requestURI.toString());
        httpExchange.sendResponseHeaders(RESPONSE_CODE_FORBIDDEN, response.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public abstract void successHandler(HttpExchange httpExchange) throws IOException;

    protected String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    protected final void print(HttpExchange httpExchange, String text, int responseCode) throws IOException {
        httpExchange.sendResponseHeaders(responseCode, text.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(text.getBytes());
        os.close();
    }

    protected final void printOk(HttpExchange httpExchange, String text) throws IOException {
        httpExchange.sendResponseHeaders(RESPONSE_CODE_OK, text.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(text.getBytes());
        os.close();
    }

    protected final void printNotFound(HttpExchange httpExchange, String text) throws IOException {
        httpExchange.sendResponseHeaders(RESPONSE_CODE_NOT_FOUND, text.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(text.getBytes());
        os.close();
    }

    protected final void printNotFound(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(RESPONSE_CODE_NOT_FOUND, PAGE_NOT_FOUND_TEXT.getBytes().length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(PAGE_NOT_FOUND_TEXT.getBytes());
        os.close();
    }
}
