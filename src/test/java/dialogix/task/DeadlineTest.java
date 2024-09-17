package dialogix.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;

class DeadlineTest {
    @Test
    void deadlineTestWithString() {
        Deadline test = new Deadline("deadline", "deadlineBy");
        assertEquals("[D][X] deadline (by: deadlineBy)", test.toString(), "toString() method works");

        test.markAsDone();
        assertEquals("[D][O] deadline (by: deadlineBy)", test.toString(), "markAsDone() method works");
    }

    @Test
    void deadlineTestWithDate() {
        Deadline test = new Deadline("deadline", new Date(0));
        assertTrue(test.toString().contains("[D][X] deadline"), "toString() method works");

        test.markAsDone();
        assertTrue(test.toString().contains("[D][O] deadline"), "markAsDone() method works");
    }

    @Test
    void deadlineTestOutputFormatWithString() {
        Deadline test = new Deadline("deadline", "deadlineBy");
        assertEquals("D | 0 | deadline | deadlineBy", test.getOutputFormat(), "getOutputFormat() method works");
        test.markAsDone();
        assertEquals("D | 1 | deadline | deadlineBy", test.getOutputFormat(), "markAsDone() method works");
    }

    @Test
    void deadlineTestOutputFormatWithDate() {
        Deadline test = new Deadline("deadline", new Date(0));
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String formattedDate = dateFormatter.format(new Date(0));
        assertEquals("D | 0 | deadline | " + formattedDate, test.getOutputFormat(), "getOutputFormat() method works");

        test.markAsDone();
        assertEquals("D | 1 | deadline | " + formattedDate, test.getOutputFormat(), "markAsDone() method works");
    }
}

