package rock.tasks;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
    static String TASK_NAME = "TEST";
    class TaskStub extends Task {
        public TaskStub() {
            super(TASK_NAME);
        }
        public LocalDate getDate() {
            return LocalDate.now();
        }
    }
    @Test
    public void getCompletedStatusDefault() {
        final Task TASK_DUMMY = new TaskStub();
        assertEquals(false, TASK_DUMMY.isCompleted());
    }

    @Test
    public void setCompletionStatus() {
        final Task TASK_DUMMY = new TaskStub();
        TASK_DUMMY.setCompleted(true);
        assertEquals(true, TASK_DUMMY.isCompleted());
    }

    @Test
    public void getName() {
        final Task TASK_DUMMY = new TaskStub();
        assertEquals(TASK_NAME, TASK_DUMMY.getName());
    }
}
