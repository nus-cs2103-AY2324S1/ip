package rock.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskDeadlineTest {
    static String TASK_NAME = "TEST";
    static String DATE_STRING = "2001-01-01";
    @Test
    public void checkEquality() {
        final TaskDeadline TASK_DUMMY_1 = new TaskDeadline(TASK_NAME, DATE_STRING);
        final TaskDeadline TASK_DUMMY_2 = new TaskDeadline(TASK_NAME, DATE_STRING);
        assertEquals(TASK_DUMMY_1, TASK_DUMMY_2);
    }

    @Test
    public void getDate() {
        final TaskDeadline TASK_DUMMY = new TaskDeadline(TASK_NAME, DATE_STRING);
        assertEquals(LocalDate.parse(DATE_STRING), TASK_DUMMY.getDate());
    }
}