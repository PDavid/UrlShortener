
package hu.example.urlshortener.persistence;

/**
 *
 * @author paksyd
 */
public interface PersistenceService {

    void persist(String shortCode, String url);

    String getUrl(String shortCode);
}
