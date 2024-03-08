import java.io.ByteArrayOutputStream;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import
        javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//import com.sun.org.apache.xml.internal.security.utils.Base64;
import java.util.Base64;

public class EncryptDecrypt {
    private static final String ALGORITHM = "AES";
    private static final String
            CIPHER_ALGORITHAM = "AES/CBC/PKCS5PADDING";
    private static final String KEY =
            "29C1EB633ECAB0CA0F52B588AE92EA31";

    private static SecretKeySpec getSecretKeySpecFromHexString(String algoCommonName, String hexString)
            throws Exception {
        byte[] encodedBytes = hexStrToByteArray(hexString);
        return new SecretKeySpec(encodedBytes,
                algoCommonName);
    }

    private static byte[] hexStrToByteArray(String hex) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(hex.length() / 2);
        for (int i = 0; i < hex.length(); i += 2) {
            String
                    output = hex.substring(i, i + 2);
            int decimal =
                    Integer.parseInt(output, 16);
            baos.write(decimal);
        }
        return baos.toByteArray();
    }

    public static byte[] copyIVAndCipher(byte[] encryptedText, byte[] iv) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        os.write(iv);
        os.write(encryptedText);
        return os.toByteArray();
    }

    //Encrypt request
    public static String aes128Encrypt(String plainText) throws Exception {
        byte[] iv = new byte[]{(byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
                0x72, 0x6F, 0x5A, (byte) 0x8E, 0x12, 0x39, (byte) 0x9C, 0x07,
                0x72, 0x6F, 0x5A};
        AlgorithmParameterSpec paramSpec = new IvParameterSpec(iv);

        /** Generate a secret key from the hex string as key */
        SecretKeySpec skeySpec = getSecretKeySpecFromHexString(ALGORITHM, KEY);

        /** Creating a cipher instance with the algorithm and padding */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHAM);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, paramSpec);

        /** generating the encrypted result */
        byte[] encrypted = cipher.doFinal(plainText.getBytes("UTF-8"));

        // To add iv in encrypted string. byte[] encryptedWithIV =
        byte[] encryptedWithIV = copyIVAndCipher(encrypted, iv);
        String encryptedResult =
                Base64.getEncoder().encodeToString(encryptedWithIV);
//                Base64.encode(encryptedWithIV);
        return encryptedResult;
    }

    //Encrypt response
    public static String aes128Decrypt(String encryptedText) throws Exception {
        SecretKeySpec skeySpec = getSecretKeySpecFromHexString(ALGORITHM, KEY);
        byte[]
                encryptedIVandTextAsBytes = Base64.getDecoder().decode(encryptedText);
        //Base64.decode(encryptedText);
/** First 16 bytes are always the IV */
        byte[] iv = Arrays.copyOf(encryptedIVandTextAsBytes, 16);
        byte[] ciphertextByte = Arrays.copyOfRange(encryptedIVandTextAsBytes, 16,
                encryptedIVandTextAsBytes.length);
// Decrypt the message
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHAM);
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, new IvParameterSpec(iv));
        byte[]
                decryptedTextBytes = cipher.doFinal(ciphertextByte);
        String decryptedResult = new
                String(decryptedTextBytes, "UTF-8");
        return decryptedResult;
    }
}
