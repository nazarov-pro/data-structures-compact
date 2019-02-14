package org.shahin.nazarov.server;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;

public class GetRequest implements Runnable {

    @Override
    public void run() {
        int id = Test.getIndex();
        System.out.println("Started id[" + id + "], " + LocalDateTime.now().toString());
        URL url = null;
        int code = 0;
        try {
            url = new URL("http://localhost:1055/health");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(1000 * 60 * 1);
            con.setReadTimeout(1000 * 60 * 1);
            code = con.getResponseCode();
            System.out.println("Finished id[" + id + "], code[" + code + "], returned [" + Test.success() + "], " + LocalDateTime.now().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
