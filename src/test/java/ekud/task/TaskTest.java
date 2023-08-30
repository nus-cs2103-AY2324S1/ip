package ekud.state;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TaskTest {
    @Test
    public void markTask_taskUnmarked_taskBecomesMarked() {
        Task task = new Task("Test task", false) {
        };
        assertEquals(task.isDone(), false);
        task.mark();
        assertEquals(task.isDone(), true);
    }

    @Test
    public void markTask_taskMarked_taskStillMarked() {
        Task task = new Task("Test task", true) {
        };
        assertEquals(task.isDone(), true);
        task.mark();
        assertEquals(task.isDone(), true);
    }

    @Test
    public void unmarkTask_taskUnmarked_taskStillUnmarked() {
        Task task = new Task("Test task", false) {
        };
        assertEquals(task.isDone(), false);
        task.unmark();
        assertEquals(task.isDone(), false);
    }

    @Test
    public void unmarkTask_taskUnmarked_taskBecomesMarked() {
        Task task = new Task("Test task", true) {
        };
        assertEquals(task.isDone(), true);
        task.unmark();
        assertEquals(task.isDone(), false);
    }
}
