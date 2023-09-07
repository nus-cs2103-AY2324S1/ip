package tasks;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;


public class TaskListTest {
    class TaskStub extends Task {
        public TaskStub() {
            super("Test");
        }
        public LocalDate getDate() {
            return LocalDate.now();
        }
    }
    @Test
    public void sizeTest() {
        TaskList testList = new TaskList();
        testList.addTask(new TaskStub());
        testList.addTask(new TaskStub());

        assertEquals(2, testList.size());
    }
}
