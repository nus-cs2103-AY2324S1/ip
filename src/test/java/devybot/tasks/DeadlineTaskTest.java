package devybot.tasks;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTaskTest {

    @Test
    public void toFileString_withDateTime_validFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 10, 14, 30);
        DeadlineTask task = new DeadlineTask("Finish project", dateTime);

        String expected = "D | 0 | Finish project | 10/9/2023 1430";
        assertEquals(expected, task.toFileString());
    }

    @Test
    public void toFileString_withDate_validFormat() {
        LocalDate date = LocalDate.of(2023, 9, 15);
        DeadlineTask task = new DeadlineTask("Submit report", date);

        String expected = "D | 0 | Submit report | 15/9/2023";
        assertEquals(expected, task.toFileString());
    }

    @Test
    public void toString_withDateTime_validFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 9, 10, 14, 30);
        DeadlineTask task = new DeadlineTask("Finish project", dateTime);

        String expected = "[D][ ] Finish project (by: Sep 10 2023 2:30 pm)";
        assertEquals(expected, task.toString());
    }

    @Test
    public void toString_withDate_validFormat() {
        LocalDate date = LocalDate.of(2023, 9, 15);
        DeadlineTask task = new DeadlineTask("Submit report", date);

        String expected = "[D][ ] Submit report (by: Sep 15 2023)";
        assertEquals(expected, task.toString());
    }
}
