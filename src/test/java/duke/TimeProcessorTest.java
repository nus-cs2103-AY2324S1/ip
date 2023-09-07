package duke;

import duke.exception.DukeDateOutOfRange;
import duke.processors.TimeProcessor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TimeProcessorTest {
    @Test
    public void ErrorTest() {
        String date;
        try {
            date = TimeProcessor.StringToDate("01234567");
        } catch (DukeDateOutOfRange e) {
            assertEquals("Either the given date is not applicable" +
                    " or the time is not given in am/pm format.", e.getMessage());
        }
    }

    @Test
    public void Test() {
        String date;
        try {
            date = TimeProcessor.StringToDate("1300pm");
        } catch (DukeDateOutOfRange e) {
            return;
        }
        assertEquals("1300pm", date);
    }


}
