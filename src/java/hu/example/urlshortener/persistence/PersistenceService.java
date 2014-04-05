
package hu.example.urlshortener.persistence;

/**
 *
 * @author paksyd
 */
public interface PersistenceService {

    public void persist(String shortCode, String url);

    public String getUrl(String shortCode);
}
