import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    public static final String delimiter = ",";

    //****************Read a CSV file*****************//
    public static String read(String csvfile) {
        try {
            File file = new File(csvfile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {//read buffer until no line left
                String[] row = line.split(delimiter);//split line by , and convert to array
                for (int i = 0; i < row.length; i++) {// array.length
                    if (i < 1) { //for 1st colummn
//                        System.out.println(i + " -> " + row[i]);
                        try{
//                         String encryptedPhone = EncryptDecrypt.aes128Encrypt(row[i]);
                            String encryptedPhone ="{ VerifyVPARequest: { SubHeader: { requestUUID: ABC123, serviceRequestId: OpenAPI, serviceRequestVersion: 1.0, channelId: TXB }, VerifyVPARequestBodyEncrypted:yWvEDTCWcA8mhpUxaoRUZods7jz+7uXoiFv7FWJMFp1gHmwDfXS5VI0LYIiaI62R3xvMzULKNM8fU1099RKI2CGQ6qHKkx5w4ApoFo4wPSK14HwQFXXR0LB5BI8Hq9DY }}";
                        String decryptedResponse = APIRequestHttps.APIRequest(encryptedPhone);
                            System.out.println(decryptedResponse);
                            String decryptedPhone = EncryptDecrypt.aes128Decrypt(row[i]);
                            return decryptedPhone;
                        }catch(Exception e){
                            return "";
                        }
                    }
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return "";
    }
}
