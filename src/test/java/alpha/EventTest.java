package alpha;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class EventTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Test
    public void makeEvent_invalidDate_dateTimeParse_exceptionThrown() {
        System.setOut(new PrintStream(outContent));
        Event.makeEvent("meeting", "2022-15-01", "2022-20-01");
        assertEquals("The date/time is in an invalid format! Enter the date in the format YYYY-MM-DD HHmm\n",
                outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void makeEvent_invalidTime_dateTimeParse_exceptionThrown() {
        System.setOut(new PrintStream(outContent));
        Event.makeEvent("meeting", "2022-01-01 2500", "2022-01-01");
        assertEquals("The date/time is in an invalid format! Enter the date in the format YYYY-MM-DD HHmm\n",
                outContent.toString());
        System.setOut(originalOut);
    }

    @Test
    public void makeEvent_missingDescription_exceptionThrown() {
        System.setOut(new PrintStream(outContent));
        Event.makeEvent("     ", "2022-01-01", "2022-01-01");
        assertEquals("Missing a description! Please enter a description between " +
                        "the start and end timings of the event.\n", outContent.toString());
        System.setOut(originalOut);
    }
}
