package duke.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void toString_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime from = LocalDateTime.parse("21/08/02 1900", formatter);
        LocalDateTime to = LocalDateTime.parse("21/08/02 2000", formatter);
        Task task = new Event("Group Meeting", from, to, false);
        task.completeTask(true);
        assertEquals("[Event] [X] Group Meeting" +
                " (from: Aug 21 2002 07:00 PM to: Aug 21 2002 08:00 PM)", task.toString());
    }

    @Test
    public void toFile_correctOutput() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
        LocalDateTime from = LocalDateTime.parse("21/08/02 1900", formatter);
        LocalDateTime to = LocalDateTime.parse("21/08/02 2000", formatter);
        Task task = new Event("Group Meeting", from, to, false);
        assertEquals("E | O | Group Meeting | " +
                "Aug 21 2002 07:00 PM - Aug 21 2002 08:00 PM", task.toFile());
    }
}
