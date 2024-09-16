package arona.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * Unit tests for the ToDoTask class.
 */
public class ToDoTaskTest {

    /**
     * Tests creating a ToDoTask with a description.
     */
    @Test
    public void createToDoTaskWithDescriptionTest() {
        String description = "Buy groceries";
        ToDoTask task = new ToDoTask(description);

        assertEquals(description, task.getDescription());
        assertEquals("[ ] ", task.getStatusIcon()); // By default, it should not be marked as done
    }

    /**
     * Tests creating a ToDoTask with a description and marking it as done.
     */
    @Test
    public void createToDoTaskWithDescriptionAndMarkedTest() {
        String description1 = "Complete homework";
        ToDoTask task1 = new ToDoTask(description1, 1);

        String description2 = "Do laundry";
        ToDoTask task2 = new ToDoTask(description2);
        task2.mark();

        assertEquals(description1, task1.getDescription());
        assertEquals("[X] ", task1.getStatusIcon());

        assertEquals(description2, task2.getDescription());
        assertEquals("[X] ", task2.getStatusIcon());
    }

    /**
     * Tests the toString method of ToDoTask.
     */
    @Test
    public void toStringTest() {
        String description = "Read a book";
        ToDoTask task = new ToDoTask(description);

        String taskString = task.toString();

        assertEquals("[T][ ] " + description, taskString);
    }
}
