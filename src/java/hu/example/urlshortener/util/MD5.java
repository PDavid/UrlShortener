package hu.example.urlshortener.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paksyd
 */
public class MD5 {
    
    private static final Logger LOGGER = Logger.getLogger(MD5.class.getName());

    public static String hashString(String text) throws NoSuchAlgorithmException {

       LOGGER.log(Level.INFO, "Hashing text: {0}", text);

        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest(text.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                // could use a for loop, but we're only dealing with a single byte
                hexString.append('0');
            }
            hexString.append(hex);
        }

        String result = hexString.toString();

       LOGGER.log(Level.INFO, "hash: {0}", result);

        return result;
    }
}
