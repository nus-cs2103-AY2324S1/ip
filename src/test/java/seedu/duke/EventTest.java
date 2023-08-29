package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.Tasks.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    public static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void eventMarkTest() {
        Event e = new Event("go to concert", LocalDateTime.parse("2019-02-01 16:00", validFormat),
                LocalDateTime.parse("2019-02-01 20:00", validFormat),false);
        e.mark();
        assertEquals("[E][X] go to concert (from:01 Feb 2019 16:00 to:01 Feb 2019 20:00)", e.toString());
    }

    @Test
    public void eventWriteFormatTest() {
        Event e = new Event("go to concert", LocalDateTime.parse("2019-02-01 16:00", validFormat),
                LocalDateTime.parse("2019-02-01 20:00", validFormat),false);
        String test = e.writeFormat();
        assertEquals("E | 0 | go to concert | 2019-02-01 16:00 | 2019-02-01 20:00", test);
    }
}