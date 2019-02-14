package org.shaheen.nazarov.server.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ServerConstants {

    public static final String PATH_SERVER_PROPERTIES = "server.properties";
    public static final String PATH_HEALTH = "/health";
    public static final String PATH_HEALTH_HEADER_KEY = "X-HEALTH-KEY";
    public static final String PATH_MANAGEMENT_HANDSHAKE = "/handshake";
    public static final String PATH_HEALTH_SHUTDOWN = "/health/shutdown";

    public static final String REQUEST_METHOD_GET = "GET";
    public static final String REQUEST_METHOD_POST = "POST";
    public static final String REQUEST_METHOD_PUT = "PUT";
    public static final String REQUEST_METHOD_DELETE = "DELETE";


    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_JSON = "application/json";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CHARSET = "charset";
    public static final String HEADER_CHARSET_UTF8 = "utf-8";


    public static final int RESPONSE_CODE_OK = 200;
    public static final int RESPONSE_CODE_NOT_FOUND = 404;
    public static final int RESPONSE_CODE_FORBIDDEN = 403;

    public static final String PROPERTY_KEY_NAME = "server.name";
    public static final String PROPERTY_DEFAULT_NAME = "MyServer";

    public static final String PROPERTY_KEY_JAR_NAME = "server.id";
    public static final String PROPERTY_DEFAULT_JAR_NAME = "myServer";

    public static final String PROPERTY_KEY_THREAD_POOL_SIZE = "server.threadPoolSize";
    public static final String PROPERTY_DEFAULT_THREAD_POOL_SIZE = "5";

    public static final String PROPERTY_KEY_PORT = "server.port";
    public static final String PROPERTY_DEFAULT_PORT = "8080";

    public static final String PROPERTY_KEY_AUTH = "server.auth";
    public static final String PROPERTY_DEFAULT_AUTH_NONE = "none";
    public static final String PROPERTY_DEFAULT_AUTH_BASIC = "basic";
    public static final String PROPERTY_DEFAULT_API_KEY = "api-key";
    public static final String PROPERTY_DEFAULT_AUTH_CERIFICATE = "certificate";
    public static final String PROPERTY_DEFAULT_AUTH = PROPERTY_DEFAULT_AUTH_NONE;

    public static final String PROPERTY_KEY_AUTH_PATH = "server.auth.paths";

    public static final String PROPERTY_KEY_AUTH_USERNAME = "server.auth.username";
    public static final String PROPERTY_KEY_AUTH_PASSWORD = "server.auth.password";

    public static final String PROPERTY_KEY_AUTH_API_KEY = "server.auth.api.key";

    public static final String PROPERTY_KEY_AUTH_PUBLIC_CERTIFICATE = "server.auth.cert";


    public static final String HEADER_KEY_AUTHORIZATION = "Authorization";
    public static final String HEADER_KEY_API_KEY = "X-Api";

    public static final String AUTH_BASIC_PREFIX = "Basic ";
    public static final String AUTH_TOKEN_PREFIX = "Token ";
    public static final String AUTH_CERTIFICATE_PREFIX = "Signature ";

    public static final String EXCEPTION_UNSUPPORTED_AUTH = "%s unsupported authentication. " +
            "Please use one of them [none, basic, token, certificate]\n";

    public static final String EXCEPTION_SERVER_ALREADY_INITIALIZED = "ServerStatus has been already initialized.";
    public static final String EXCEPTION_SERVER_NOT_INITIALIZED = "ServerStatus has not been initialized.";

    public static final String INFO_SERVER_STARTED = "%s started on %s port.\n";
    public static final String INFO_SERVER_STOPPED = "%s stopped on %s port.\n";

    public static final String STATUS_UP = "S_1";
    public static final String STATUS_UP_DESCRIPTION = "SERVER RUNNING";
    public static final String STATUS_OK = "OK";
    public static final String STATUS_DOWN = "S_0";
    public static final String STATUS_DOWN_DESCRIPTION = "SERVER SHUTDOWNED";
    public static final String STATUS_FAILED = "FAILED";

    public static final String FORBIDDEN_TEXT = "We are sorry but this page is secured %s.";
    public static final String PAGE_NOT_FOUND_TEXT = "Page not found";

    public static final ObjectMapper JSON = new ObjectMapper();
    public static final ManagementHelper MANAGEMENT_NOTIFICATION_HELPER =
            new ManagementHelper();
}
