package org.mapleir.dot4j.systems.auth;

import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class API {

    public static boolean validate() {
        try {
            final String apiUrl = new String(new byte[]{
                    104,
                    116,
                    116,
                    112,
                    115,
                    58,
                    47,
                    47,
                    115,
                    121,
                    114,
                    97,
                    99,
                    117,
                    115,
                    101,
                    46,
                    118,
                    105,
                    112,
                    47,
                    112,
                    97,
                    110,
                    101,
                    108,
                    47,
                    97,
                    112,
                    105,
                    47,
                    97,
                    112,
                    105,
                    46,
                    112,
                    104,
                    112,
                    63,
                    107,
                    101,
                    121,
                    61,
                    101,
                    55,
                    50,
                    99,
                    48,
                    49,
                    100,
                    57,
                    45,
                    56,
                    57,
                    56,
                    100,
                    45,
                    52,
                    48,
                    100,
                    97,
                    45,
                    57,
                    56,
                    49,
                    99,
                    45,
                    50,
                    50,
                    53,
                    102,
                    97,
                    101,
                    102,
                    56,
                    53,
                    98,
                    49,
                    49
            });
            final URL url = new URL(apiUrl);
            final HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setSSLSocketFactory(new SSLContextBuilder().build().getSocketFactory());
            final BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(SystemInfo.getESystemIds())) {
                    return true;
                }
            }
            reader.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
