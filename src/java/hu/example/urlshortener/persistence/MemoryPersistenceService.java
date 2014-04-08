package hu.example.urlshortener.persistence;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author paksyd
 */
public class MemoryPersistenceService implements PersistenceService {

    private static final Logger LOGGER = Logger.getLogger(MemoryPersistenceService.class.getName());

    private static MemoryPersistenceService instance;

    private final Map<String, String> map;

    private MemoryPersistenceService() {
        map = new ConcurrentHashMap<>();
    }

    public static MemoryPersistenceService getInstance() {
        if (instance == null) {
            instance = new MemoryPersistenceService();
        }
        return instance;
    }

    @Override
    public void persist(String shortCode, String url) {
        LOGGER.log(Level.INFO, "Persisting shortcode-url relation (shortCode: {0}, url: {1}).", 
                new Object[]{shortCode, url});

        map.put(shortCode, url);

        dumpMap();
    }

    @Override
    public String getUrl(String shortCode) {
        String url = map.get(shortCode);

        LOGGER.log(Level.INFO, "Returning a url for shortcode. (shortCode: {0}, url: {1}).", 
                new Object[]{shortCode, url});

        dumpMap();

        return url;
    }

    private void dumpMap() {
        LOGGER.log(Level.INFO, "map: {0}", map);
    }
}
