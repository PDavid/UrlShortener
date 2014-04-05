
package hu.example.urlshortener.test;

import hu.example.urlshortener.UrlShortener;
import java.security.NoSuchAlgorithmException;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author paksyd
 */
public class UrlShortenerTest {
    
    public UrlShortenerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testUrlLookup() throws NoSuchAlgorithmException {
        String randomUrl = "Test URL: " + Math.random() * 10;
        
        System.out.println("randomUrl: " + randomUrl);
        
        String shortCode = UrlShortener.createShortCodeForUrl(randomUrl);
        
        System.out.println("shortCode: " + shortCode);
        
        Assert.assertEquals(randomUrl, UrlShortener.getUrlForShortCode(shortCode));
    }
}
