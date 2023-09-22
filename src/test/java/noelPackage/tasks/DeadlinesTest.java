package noelPackage.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlinesTest {

    @Test
    public void testStringFormat() {
        LocalDate date = LocalDate.parse("2023-02-03");
        LocalTime time = LocalTime.parse("09:00");
        Deadlines testDeadline1 = new Deadlines("test", date, time);

        String actualOutput = "[D][ ] test (by: 2023-02-03 09:00)";
        assertEquals(actualOutput, testDeadline1.toString());
    }

    @Test
    public void testFileFormat() {
        LocalDate date = LocalDate.parse("2023-02-03");
        LocalTime time = LocalTime.parse("09:00");
        Deadlines testDeadline2 = new Deadlines("test", date, time);

        String actualOutput = "D | 0 | test | 2023-02-03 09:00";
        assertEquals(actualOutput, testDeadline2.toFileString());
    }

}
