package rock.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskDeadlineTest {
    static final String TASK_NAME = "TEST";
    static final String DATE_STRING = "2001-01-01";
    @Test
    public void checkEquality() {
        TaskDeadline dummyTask1 = new TaskDeadline(TASK_NAME, DATE_STRING);
        TaskDeadline dummyTask2 = new TaskDeadline(TASK_NAME, DATE_STRING);
        assertEquals(dummyTask1, dummyTask2);
    }

    @Test
    public void getDate() {
        TaskDeadline dummyTask = new TaskDeadline(TASK_NAME, DATE_STRING);
        assertEquals(LocalDate.parse(DATE_STRING), dummyTask.getDate());
    }
}
