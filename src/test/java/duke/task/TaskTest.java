package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @Test
    void getStatusIconTest() {
        Task task1 = new Task("Sample Task");
        assertFalse(task1.isDone());
        assertEquals(" ", task1.getStatusIcon());

        Task task2 = new Task("Completed Task", true);
        assertTrue(task2.isDone());
        assertEquals("X", task2.getStatusIcon());
    }

    @Test
    void getDescriptionTest() {
        Task task1 = new Task("Sample Task");
        assertEquals("Sample Task", task1.getDescription());

        Task task2 = new Task("Another Task");
        assertEquals("Another Task", task2.getDescription());
    }

    @Test
    void markAsDoneTest() {
        Task task = new Task("Sample Task");
        assertFalse(task.isDone());

        task.markAsDone();
        assertTrue(task.isDone());
    }

    @Test
    void markAsNotDoneTest() {
        Task task = new Task("Sample Task", true);
        assertTrue(task.isDone());

        task.markAsNotDone();
        assertFalse(task.isDone());
    }

    @Test
    public void toStringTest() {
        Task task = new Task("Sample Task");
        assertEquals("[ ] Sample Task", task.toString());

        task.markAsDone();
        assertEquals("[X] Sample Task", task.toString());
    }
}