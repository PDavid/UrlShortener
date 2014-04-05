package hu.example.urlshortener.test;

import hu.example.urlshortener.persistence.PersistenceService;
import hu.example.urlshortener.persistence.MemoryPersistenceService;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author paksyd
 */
public class MemoryPersistenceServiceTest {

    private static PersistenceService persistenceProvider;

    public MemoryPersistenceServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        persistenceProvider = MemoryPersistenceService.getInstance();
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
