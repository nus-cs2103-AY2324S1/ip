package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {


    @Test
    public void testMarkDone() {
        Event temp = new Event("CS2103T Project Meeting", LocalDateTime.parse("2023-08-31T12:00:00"),
                LocalDateTime.parse("2023-08-31T15:00:00"));
        temp.markDone();
        String expectedOutput = "[E][X] CS2103T Project Meeting (from: Aug 31 2023 12:00PM to: Aug 31 2023 03:00PM)";
        assertEquals(expectedOutput, temp.toString());
    }

    @Test
    public void testLineToBeWritten() {
        Deadline temp = new Deadline("iP Tasks", LocalDateTime.parse("2023-09-01T23:59:50"));
        String expectedOutput = "D |   | iP Tasks | Sep 01 2023 11:59PM";
        assertEquals(expectedOutput, temp.lineToWriteFile());
    }
}
