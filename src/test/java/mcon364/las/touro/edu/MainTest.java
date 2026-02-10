package mcon364.las.touro.edu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    @Test
    void testGetUserName(){
        assertFalse(Main.getUserName("USERNAME").isEmpty());
        assertEquals(System.getenv("USERNAME") , Main.getUserName("USERNAME").get());
        assertTrue(Main.getUserName("NULL").isEmpty());
    }


    @Test
    void testGetGreeting(){
        // we can pass in any NON environment variable key name to getGreeting() to make it fail
        // USERNAME is a built in environment variable in every system
        assertEquals("Hello, Guest", Main.getGreeting("NULL"));
        assertEquals("Hello, " + System.getenv("USERNAME"), Main.getGreeting("USERNAME"));
    }

    @Test
    void testProcessValues() {
        assertEquals(2, Main.processValues(List.of(
                List.of(5, 10, 15),     // Processes completely
                List.of(20, 0, 25),     // Finds 0, skips to next list
                List.of(30, 35, 40),    // Processes completely
                List.of(45, 99, 50),    // Finds 99, terminates everything
                List.of(55, 60, 65)     // Never reached
                )
        ));
    }
}
