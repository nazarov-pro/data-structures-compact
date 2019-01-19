package org.shaheen.nazarov.server.util;

public class ServerConstants {

    public static final String PATH_SERVER_PROPERTIES = "server.properties";
    public static final String PATH_HEALTH = "/health";
    public static final String PATH_HEALTH_SHUTDOWN = "/health/shutdown";

    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_PUT = "PUT";
    public static final String REQUEST_METHOD_DELETE = "DELETE";

    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_NOT_FOUND = 404;
    public static final int RESPONSE_CODE_FORBIDDEN = 403;

    public static final String PROPERTY_KEY_NAME = "server.name";
    public static final String PROPERTY_DEFAULT_NAME = "MyServer";

    public static final String PROPERTY_KEY_THREAD_POOL_SIZE = "server.threadPoolSize";
    public static final String PROPERTY_DEFAULT_THREAD_POOL_SIZE = "5";

    public static final String PROPERTY_KEY_PORT = "server.port";
    public static final String PROPERTY_DEFAULT_PORT = "8080";

    public static final String PROPERTY_KEY_AUTH = "server.auth";
    public static final String PROPERTY_DEFAULT_AUTH_NONE = "none";
    public static final String PROPERTY_DEFAULT_AUTH_BASIC = "basic";
    public static final String PROPERTY_DEFAULT_AUTH_TOKEN = "token";
    public static final String PROPERTY_DEFAULT_AUTH_CERIFICATE = "certificate";
    public static final String PROPERTY_DEFAULT_AUTH = PROPERTY_DEFAULT_AUTH_NONE;

    public static final String PROPERTY_KEY_AUTH_PATH = "server.auth.paths";

    public static final String PROPERTY_KEY_AUTH_USERNAME = "server.auth.username";
    public static final String PROPERTY_KEY_AUTH_PASSWORD = "server.auth.password";

    public static final String PROPERTY_KEY_AUTH_TOKEN = "server.auth.token";

    public static final String PROPERTY_KEY_AUTH_PUBLIC_CERTIFICATE = "server.auth.cert";


    public static final String HEADER_KEY_AUTHORIZATION = "Authorization";

    public static final String AUTH_BASIC_PREFIX = "Basic ";
    public static final String AUTH_TOKEN_PREFIX = "Token ";
    public static final String AUTH_CERTIFICATE_PREFIX = "Signature ";

    public static final String EXCEPTION_UNSUPPORTED_AUTH = "%s unsupported authentication. " +
            "Please use one of them [none, basic, token, certificate]\n";

    public static final String EXCEPTION_SERVER_ALREADY_INITIALIZED = "ServerStatus has been already initialized.";
    public static final String EXCEPTION_SERVER_NOT_INITIALIZED = "ServerStatus has not been initialized.";

    public static final String INFO_SERVER_STARTED = "%s started on %s port.\n";
    public static final String INFO_SERVER_STOPPED = "%s stopped on %s port.\n";

    public static final String STATUS_UP = "UP";
    public static final String STATUS_FAILED = "FAILED";

    public static final String FORBIDDEN_TEXT = "We are sorry but this page is secured %s.";
    public static final String PAGE_NOT_FOUND_TEXT = "We are sorry but this page is secured %s.";
}