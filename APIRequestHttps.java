import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class APIRequestHttps {
    public static String apiURL = "https://bank.co.in/gateway/api/txb/v1/acct-recon/verifyVPA";
    public static String clientCertPath = "C:/Users/abc/client_certificate.pfx"; // Path to client certificate file
    public static String clientCertPassword = "12345678"; // Password for the client certificate

    public static String APIRequest(String postData) {
        try {
            // Load the client certificate
            KeyStore clientKeyStore = KeyStore.getInstance("PKCS12");
            clientKeyStore.load(new FileInputStream(clientCertPath), clientCertPassword.toCharArray());

            // Initialize KeyManagerFactory with client keystore
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(clientKeyStore, clientCertPassword.toCharArray());

            // Initialize SSLContext with client's key material
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(keyManagerFactory.getKeyManagers(), null, null);

            // Get SSLSocketFactory from SSLContext
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            // Open HTTPS connection
            URL url = new URL(apiURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setSSLSocketFactory(sslSocketFactory);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setDoOutput(true);

            // Write data to output stream
            try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
                outputStream.writeBytes(postData);
                outputStream.flush();
            }

            // Get response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read response from the API
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                System.out.println("Response: --" + response.toString());
                return response.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  "";
    }
}
