package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {

    @Test
    public void stringToFile_correctDateTimeFormat_success() throws Exception {
        assertEquals("D | 0 | CS2103 W3 iP | 2023-08-31 23:59",
                new Deadline("CS2103 W3 iP", "2023-08-31 23:59")
                        .stringToFile());

        assertEquals("D | 0 | CS2030 Lab 2 | 2022-09-30 23:59",
                new Deadline("CS2030 Lab 2", "2022-09-30 23:59")
                        .stringToFile());
    }

    @Test
    public void stringToFile_wrongDateTimeFormat_exceptionThrown() {
        try {
            assertEquals("D | 0 | CS2103 W3 iP | 2023-08-31 23:59",
                    new Deadline("CS2103 W3 iP", "31 Aug 2023 23:59")
                            .stringToFile());
            fail();
        } catch (Exception e) {
            assertEquals(" ☹ Please enter datetime in format yyyy-MM-dd HH:mm",
                    e.getMessage());
        }
    }

    @Test
    public void stringToFile_impossibleDateTimeFormat_exceptionThrown() {
        try {
            assertEquals("D | 0 | CS2103 W3 iP | 2023-08-32 23:59",
                    new Deadline("CS2103 W3 iP", "31 Aug 2023 23:59")
                            .stringToFile());
            fail();
        } catch (Exception e) {
            assertEquals(" ☹ Please enter datetime in format yyyy-MM-dd HH:mm",
                    e.getMessage());
        }
    }

    @Test
    public void testStringConversion() throws Exception {
        assertEquals("[D][ ] Homework (by: 28 Aug 2023 16:00)",
                new Deadline("Homework", "2023-08-28 16:00")
                        .toString());
    }
}
