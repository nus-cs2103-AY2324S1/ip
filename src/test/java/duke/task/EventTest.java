package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class EventTest {

    @Test
    public void check_toString_output() {
        // Creation of new ToDo results in correct toString output
        try {
            assertEquals("[E] [ ] Party (from: 12 Dec 2023 1800 to: 12 Dec 2023 2000)",
                    new Event("Party", "2023-12-12 1800", "2023-12-12 2000").toString());
        } catch (Exception e) {
            //should not have an exception
        }
    }

    @Test
    public void check_invalidInput_exceptionThrown() {
        try {
            assertEquals("", new Event("Party",
                    "2023-1-1 1800",
                    "2023-12-12 2000").toString());
            fail(); //test should not reach this line
        } catch (Exception e) {
            assertEquals("Text '2023-1-1' could not be parsed at index 5", e.getMessage());
        }
    }
}
