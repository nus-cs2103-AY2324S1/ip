package duke.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class DeadlineTest {
    @Test
    public void toString_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime by = LocalDateTime.parse("21/08/02 1900", formatter);
        Task task = new Deadline("Finish HW", by);
        task.completeTask(true);
        assertEquals("[D][X] Finish HW (by: Aug 21 2002 07:00 PM)", task.toString());
    }

    @Test
    public void toFile_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime by = LocalDateTime.parse("21/08/02 1900", formatter);
        Task task = new Deadline("Finish HW", by, "0");
        assertEquals("D | 0 | Finish HW | Aug 21 2002 07:00 PM", task.toFile());
    }
}
