package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.duke.tasks.Deadline;

public class DeadlineTest {

    private static DateTimeFormatter validFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void deadlineMarkTest() {
        Deadline d = new Deadline("return book", LocalDateTime.parse("2020-02-20 12:00", validFormat), false);
        d.mark();
        assertEquals("[D][X] return book (by: 20 Feb 2020 12:00)", d.toString());
    }

    @Test
    public void deadlineWriteFormatTest() {
        Deadline d = new Deadline("CS2103T due", LocalDateTime.parse("2021-08-22 17:19", validFormat), false);
        String test = d.writeFormat();
        assertEquals("D | 0 | CS2103T due | 2021-08-22 17:19", test);
    }
}
