package rock.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskEventTest {
    static String TASK_NAME = "TEST";
    static String START_DATE_STRING = "2001-01-01";
    static String END_DATE_STRING = "2001-12-12";
    @Test
    public void checkEquality() {
        final TaskEvent TASK_DUMMY_1 = new TaskEvent(TASK_NAME, START_DATE_STRING, END_DATE_STRING);
        final TaskEvent TASK_DUMMY_2 = new TaskEvent(TASK_NAME, START_DATE_STRING, END_DATE_STRING);
        assertEquals(TASK_DUMMY_1, TASK_DUMMY_2);
    }

    @Test
    public void ifStartBeforeEnd_ThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new TaskEvent(TASK_NAME, END_DATE_STRING, START_DATE_STRING));
    }
    @Test
    public void getDate() {
        final TaskEvent TASK_DUMMY = new TaskEvent(TASK_NAME, START_DATE_STRING, END_DATE_STRING);
        assertEquals(LocalDate.parse(START_DATE_STRING), TASK_DUMMY.getDate());
    }
}