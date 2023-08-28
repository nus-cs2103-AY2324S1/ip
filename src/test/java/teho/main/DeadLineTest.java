package teho.main; //same package as the class being tested

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadLineTest {
    @Test
    public void toStringTest() {
        LocalDate byDate = LocalDate.parse("2019-10-15");
        String command = "Do homework";
        Task task = new Deadline(command, byDate);
        assertEquals("[D][ ] Do homework (by: Oct 15 2019)", task.toString());
    }

    @Test
    public void fileStringTest() {
        LocalDate byDate = LocalDate.parse("2019-10-15");
        String command = "Do assignment";
        Task task = new Deadline(command, byDate);
        assertEquals("D|0|Do assignment|2019-10-15", task.fileString());
    }
}
