package duck.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

public class DeadlineTaskTest {
    @Test
    public void stringify_success() {
        DeadlineTask task = new DeadlineTask("return book", false, LocalDate.parse("2023-09-01"));
        assertEquals("D011/return book11/Sep 01 2023", task.stringify());

        task = new DeadlineTask("submit application", true, LocalDate.parse("2023-10-01"));
        assertEquals("D118/submit application11/Oct 01 2023", task.stringify());
    }

    @Test
    public void parse_success() {
        try {
            DeadlineTask task = new DeadlineTask("return book", false, LocalDate.parse("2023-09-01"));
            String taskString = task.stringify();
            Task parsedTask = DeadlineTask.parse(taskString);
            assertEquals(task, parsedTask);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void toString_success() {
        DeadlineTask task = new DeadlineTask("return book", false, LocalDate.parse("2023-09-01"));
        assertEquals("[D][ ] return book (by: Sep 01 2023)", task.toString());

        task = new DeadlineTask("submit application", true, LocalDate.parse("2023-10-01"));
        assertEquals("[D][X] submit application (by: Oct 01 2023)", task.toString());
    }
}
