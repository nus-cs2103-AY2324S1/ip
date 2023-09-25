package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    private DtFormat dtf = new DtFormat();

    @Test
    public void testShortString() throws DukeException {
        Deadline deadline = new Deadline("help", true, "/by 8/8/2020 1630");
        assertEquals(deadline.toShortString(), "D");
    }
    @Test
    public void testFormatDeadline() throws DukeException {
        Deadline deadline = new Deadline("help", true, "/by 8/8/2020 1630");
        String formattedDeadline = deadline.getFormattedDatetime(dtf.getOutFormatter());
        assertEquals(formattedDeadline, "(by: 2020-08-08 16:30)");
    }
}
