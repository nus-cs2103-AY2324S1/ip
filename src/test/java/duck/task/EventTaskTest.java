package duck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

public class EventTaskTest {
    @Test
    public void stringify_success() {
        EventTask task = new EventTask("career fair", false, LocalDate.parse("2023-08-29"), LocalDate.parse("2023-08-30"));
        assertEquals("E011/career fair11/Aug 29 202311/Aug 30 2023", task.stringify());

        task = new EventTask("TI 23", true, LocalDate.parse("2023-10-12"), LocalDate.parse("2023-10-29"));
        assertEquals("E15/TI 2311/Oct 12 202311/Oct 29 2023", task.stringify());
    }

    @Test
    public void parse_success() {
        try {
            EventTask task = new EventTask("career fair", false, LocalDate.parse("2023-08-29"), LocalDate.parse("2023-08-30"));
            String taskString = task.stringify();
            Task parsedTask = EventTask.parse(taskString);
            assertEquals(task, parsedTask);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toString_success() {
        EventTask task = new EventTask("career fair", false, LocalDate.parse("2023-08-29"), LocalDate.parse("2023-08-30"));
        assertEquals("[E][ ] career fair (from: Aug 29 2023 to Aug 30 2023)", task.toString());

        task = new EventTask("TI 23", true, LocalDate.parse("2023-10-12"), LocalDate.parse("2023-10-29"));
        assertEquals("[E][X] TI 23 (from: Oct 12 2023 to Oct 29 2023)", task.toString());
    }
}
