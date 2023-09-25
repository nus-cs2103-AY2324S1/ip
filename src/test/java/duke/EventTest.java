package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EventTest {
    private DtFormat dtf = new DtFormat();

    @Test
    public void testShortString() throws DukeException {
        Event event = new Event("build sandcastle", true, "/from 18/8/2020 1700 /to 18/8/2020 1800");
        assertEquals(event.toShortString(), "E");
    }
    @Test
    public void testFormatDeadline() throws DukeException {
        Event event = new Event("build sandcastle", true, "/from 18/8/2020 1700 /to 18/8/2020 1800");
        String formattedDeadline = event.getFormattedDatetime(dtf.getOutFormatter());
        assertEquals(formattedDeadline, "(from: 2020-08-18 17:00 to: 2020-08-18 18:00)");
    }
}
