package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {
    @Test
    public void testSplitEventString() {
        String testString = "event project meeting /from 2019-10-15 1800 /to 2019-10-15 2000";
        String[] arr = Event.splitEventString(testString);
        assertEquals("project meeting", arr[0].trim());
        assertEquals("2019-10-15 1800", arr[1].trim());
        assertEquals("2019-10-15 2000", arr[2].trim());
    }

    @Test
    public void emptyEvent() throws IllegalArgumentException {
        String testString = "event";

        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, 
            () -> {
                Event.splitEventString(testString);
            });

        String expectedErrorMsg = "OOPS!!! The description of a event cannot be empty.";
        String actualErrorMsg = expectedException.getMessage();
        assertEquals(expectedErrorMsg, actualErrorMsg); 
    }

    @Test 
    public void missingDescription() throws IllegalArgumentException {
        String testString = "event /from 2019-10-15 1800 /to";

        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, 
            () -> {
                Event.splitEventString(testString);
            });
        
        String expectedErrorMsg = "OOPS!!! The description of a event cannot be empty.";
        String actualErrorMsg = expectedException.getMessage();
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test 
    public void missingFrom() throws IllegalArgumentException {
        String testString = "event project meeting /to 2019-10-15 2000";

        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, 
            () -> {
                Event.splitEventString(testString);
            });
        
        String expectedErrorMsg = "Invalid event format: missing /from";
        String actualErrorMsg = expectedException.getMessage();
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }

    @Test
    public void missingTo() throws IllegalArgumentException {
        String testString = "event project meeting /from 2019-10-15 1800";
        
        IllegalArgumentException expectedException = assertThrows(IllegalArgumentException.class, 
            () -> {
                Event.splitEventString(testString);
            });
        
        String expectedErrorMsg = "Invalid event format: missing /to";
        String actualErrorMsg = expectedException.getMessage();
        assertEquals(expectedErrorMsg, actualErrorMsg);
    }
}
