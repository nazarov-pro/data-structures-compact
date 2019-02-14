package org.shaheen.nazarov.server.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.shaheen.nazarov.server.domain.Handshake;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import static org.shaheen.nazarov.server.util.ServerConstants.*;

public class ManagementHelper {

    private ServerProperty serverProperty = ServerPropertySingleton.getInstance();

    public boolean start() {
        return handshake(true);
    }

    public boolean stop() {
        return handshake(false);
    }

    private boolean handshake(boolean start) {
        Handshake handshake = new Handshake();
        handshake.setJarFileName(serverProperty.getJarFileName());
        handshake.setStatus(start ? STATUS_UP : STATUS_DOWN);
        if (handshake.getStatus().equals(STATUS_UP)) {
            handshake.setName(serverProperty.getName());
            handshake.setPort(serverProperty.getPort());
            handshake.setHost(serverProperty.getHost());
        }
        String body = null;
        try {
            body = JSON.writeValueAsString(handshake);
        } catch (JsonProcessingException e) {
            return false;
        }
        return send(serverProperty.getServerManagementEndpoint() + PATH_MANAGEMENT_HANDSHAKE, body);
    }

    private boolean send(String targetPath, String body) {
        try {
            URL url = new URL(targetPath);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod(REQUEST_METHOD_POST);
            con.setDoOutput(true);
            con.setInstanceFollowRedirects(false);
            con.setRequestProperty(HEADER_CONTENT_TYPE, HEADER_CONTENT_TYPE_JSON);
            con.setRequestProperty(HEADER_CHARSET, HEADER_CHARSET_UTF8);
            con.setRequestProperty(HEADER_CONTENT_LENGTH, String.valueOf(body.length()));
            con.setUseCaches(false);
            con.setConnectTimeout(1000 * 60 * 1);
            con.setReadTimeout(1000 * 60 * 1);

            try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
                wr.write(body.getBytes());
            }

            if (con.getResponseCode() == RESPONSE_CODE_OK) return true;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

