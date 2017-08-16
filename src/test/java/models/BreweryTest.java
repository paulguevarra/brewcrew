package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Guest on 8/16/17.
 */
public class BreweryTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void NewBreweryObjectGetsCorrectlyCreated_true() throws Exception {
        Brewery brewery = setupNewBrewery();
        assertEquals(true, brewery instanceof Brewery);
    }

    @Test
    public void BreweryInstantiatesWithContent_true() throws Exception {
        Brewery brewery = setupNewBrewery();
        assertEquals("Rouge Ale House", brewery.getDescription());
    }


//helper method
    public Brewery setupNewBrewery(){
        return new Brewery("Rouge Ale House");
    }
}