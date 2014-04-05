package hu.example.urlshortener;

import hu.example.urlshortener.persistence.PersistenceProvider;
import hu.example.urlshortener.persistence.PersistenceProviderMap;
import hu.example.urlshortener.util.MD5;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paksyd
 */
public class UrlShortener {

    private static final Logger LOGGER = Logger.getLogger(UrlShortener.class.getName());

    public static String createShortCodeForUrl(String url) throws NoSuchAlgorithmException {

        LOGGER.log(Level.INFO, "Creating shortcode for url: {0}", url);

        String shortCode = MD5.hashString(url).substring(0, 6);

        // TODO: handle collisions!
        PersistenceProvider persistenceProvider = PersistenceProviderMap.getInstance();
        persistenceProvider.persist(shortCode, url);

        LOGGER.log(Level.INFO, "Created shortcode: {0}", shortCode);

        return shortCode;
    }

    public static String getUrlForShortCode(String shortCode) {
        LOGGER.log(Level.INFO, "Getting url for shortcode: {0}", shortCode);

        PersistenceProvider persistenceProvider = PersistenceProviderMap.getInstance();
        String url = persistenceProvider.getUrl(shortCode);

        LOGGER.log(Level.INFO, "Url for shortcode is: {0}", url);

        return url;
    }
}
