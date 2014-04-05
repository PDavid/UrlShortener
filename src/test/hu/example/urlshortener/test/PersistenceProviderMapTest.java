package hu.example.urlshortener.test;

import hu.example.urlshortener.persistence.PersistenceProvider;
import hu.example.urlshortener.persistence.PersistenceProviderMap;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author paksyd
 */
public class PersistenceProviderMapTest {

    private static PersistenceProvider persistenceProvider;

    public PersistenceProviderMapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        persistenceProvider = PersistenceProviderMap.getInstance();
    }

    @Test
    public void testLookup() {
        String randomKey = "Test key: " + Math.random() * 10;
        String value = "Foo";

        System.out.println("randomKey: " + randomKey);
        System.out.println("value: " + value);

        persistenceProvider.persist(randomKey, value);
        
        Assert.assertEquals(value, persistenceProvider.getUrl(randomKey));
    }
}
