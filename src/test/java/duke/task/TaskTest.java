package duke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTest {
    @Test
    public void markAsDone_success() {
        Task task = new Task("test");
        assertEquals(task.getStatusIcon(), " ");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
    }

    @Test
    public void markAsUndone_success() {
        Task task = new Task("test");
        assertEquals(task.getStatusIcon(), " ");
        task.markAsDone();
        assertEquals(task.getStatusIcon(), "X");
        task.markAsUndone();
        assertEquals(task.getStatusIcon(), " ");
        task.markAsUndone();
        assertEquals(task.getStatusIcon(), " ");
    }

    @Test
    public void toString_success() {
        Task task = new Task("test");
        assertEquals(task.toString(), "[ ] test");
        task.markAsDone();
        assertEquals(task.toString(), "[X] test");
        task.markAsUndone();
        assertEquals(task.toString(), "[ ] test");
    }

    @Test
    public void toSaveString_success() {
        Task task = new Task("test");
        assertEquals(task.toSaveString(), "0|test");
        task.markAsDone();
        assertEquals(task.toSaveString(), "1|test");
        task.markAsUndone();
        assertEquals(task.toSaveString(), "0|test");
    }
}
