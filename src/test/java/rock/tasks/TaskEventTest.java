package rock.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskEventTest {
    static final String TASK_NAME = "TEST";
    static final String START_DATE_STR = "2001-01-01";
    static final String END_DATE_STR = "2001-12-12";
    @Test
    public void checkEquality() {
        TaskEvent dummyTask1 = new TaskEvent(TASK_NAME, START_DATE_STR, END_DATE_STR);
        TaskEvent dummyTask2 = new TaskEvent(TASK_NAME, START_DATE_STR, END_DATE_STR);
        assertEquals(dummyTask1, dummyTask2);
    }

    @Test
    public void ifStartBeforeEndThrowError() {
        assertThrows(IllegalArgumentException.class, () -> new TaskEvent(TASK_NAME, END_DATE_STR, START_DATE_STR));
    }
    @Test
    public void getDate() {
        TaskEvent dummyTask = new TaskEvent(TASK_NAME, START_DATE_STR, END_DATE_STR);
        assertEquals(LocalDate.parse(START_DATE_STR), dummyTask.getDate());
    }
}
