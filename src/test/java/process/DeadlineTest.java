package process;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private Deadline process = new Deadline();
    @Test
    public void testFirstInput() {
        String output = process.processInput("test");
        assertEquals("Now indicate the deadline date.", output);
    }

    @Test
    public void testInvalidDate() {
        process.processInput("test");
        String output = process.processInput("32 Aug 2023");
        assertEquals("The given date is invalid. Please type in a valid date.", output);
    }

    @Test
    public void testSecondInput() {
        process.processInput("test");
        String output = process.processInput("09 Aug 2023");
        assertEquals("Indicate a start time ranging from 0000 - 2359. "
                + "You may enter 'Skip' to not indicate a time", output);
    }

    @Test
    public void testInvalidTime() {
        process.processInput("test");
        process.processInput("10 Aug 2023");
        String output = process.processInput("2400");
        assertEquals("The given time is invalid. "
                + "Please type in a valid time in the range between 0000 - 2359", output);
    }

    @Test
    public void testIsComplete() {
        process.processInput("test");
        process.processInput("09 Aug 2023");
        process.processInput("skip");
        assertEquals(true, process.isComplete());
    }
}
