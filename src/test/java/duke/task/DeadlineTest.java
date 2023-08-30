package duke.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest extends TaskTest {
    @Test
    public void testToString() {
        LocalDate byDate = LocalDate.of(2023, 9, 10);
        Deadline deadline = new Deadline("Finish CS2103", byDate);
        assertEquals("[D][ ] Finish CS2103 (by: Sep 10 2023)", deadline.toString());
    }

    @Test
    public void testSaveString() {
        LocalDate byDate = LocalDate.of(2023, 9, 10);
        Deadline deadline = new Deadline("Finish CS2101", byDate);
        assertEquals("0/Finish CS2101/2023-09-10", deadline.saveString());
    }
}
