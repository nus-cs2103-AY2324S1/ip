package rock.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TaskTest {
    static final String TASK_NAME = "TEST";
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
        Task dummyTask = new TaskStub();
        assertEquals(false, dummyTask.isCompleted());
    }

    @Test
    public void setCompletionStatus() {
        Task dummyTask = new TaskStub();
        dummyTask.setCompleted(true);
        assertEquals(true, dummyTask.isCompleted());
    }

    @Test
    public void getName() {
        Task dummyTask = new TaskStub();
        assertEquals(TASK_NAME, dummyTask.getName());
    }
}
