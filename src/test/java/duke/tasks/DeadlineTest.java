package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void toString_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime by = LocalDateTime.parse("21/08/02 1900", formatter);
        Task task = new Deadline("Finish HW", by, false);
        task.completeTask(true);
        assertEquals("[Deadline] [X] Finish HW" +
                " (by: Aug 21 2002 07:00 PM)", task.toString());
    }

    @Test
    public void toFile_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime by = LocalDateTime.parse("21/08/02 1900", formatter);
        Task task = new Deadline("Finish HW", by, false);
        assertEquals("D | O | Finish HW | " +
                "Aug 21 2002 07:00 PM", task.toFile());
    }
}
