package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

public class TaskListTest {

    @Test
    public void addTask_validTask_expectedBehaviour() {
        TaskList taskList = new TaskList();
        assertEquals(0, taskList.getSize());
        Ui ui = new Ui();

        try {
            String s = taskList.addTask(new ToDo("Test Task"), ui);
            assertEquals(1, taskList.getSize());
            assertEquals("Got it. I've added this task:\n" +
                    "[T][ ] Test Task\n" +
                    "Now you have 1 task(s) in the list.", s);
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
    }

    @Test
    public void removeTask_validTask_expectedBehaviour() {
        // Create a TaskList with some tasks
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();

        try {
            tasks.add(new ToDo("Task 1"));
            tasks.add(new Deadline("Task 2", "2023-08-23 1800"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        TaskList taskList = new TaskList(tasks);
        assertEquals(2, taskList.getSize());

        // Remove a task
        String s = taskList.removeTask(0, ui);
        assertEquals(1, taskList.getSize());
        assertEquals("Noted. I've removed this task:\n" +
                "[T][ ] Task 1\n" +
                "Now you have 1 task(s) in the list.", s);
    }

    @Test
    public void markTask_validTask_expectedBehaviour() {
        // Create a TaskList with a task
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();

        try {
            tasks.add(new ToDo("Task 1"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }

        TaskList taskList = new TaskList(tasks);

        // Mark the task as done
        String s = taskList.markTask(0, ui);

        // Check if the task is marked as done
        assertTrue(tasks.get(0).isDone());
        assertEquals("Nice! I've marked this task as done:\n" +
                "[T][X] Task 1\n", s);
    }

    @Test
    public void unmarkTask_validTask_expectedBehaviour() {
        // Create a TaskList with a done task
        ArrayList<Task> tasks = new ArrayList<>();
        Ui ui = new Ui();
        try {
            tasks.add(new ToDo("Task 1"));
        } catch (DukeException e) {
            fail("Exception should not be thrown: " + e.getMessage());
        }
        tasks.get(0).markAsDone();

        TaskList taskList = new TaskList(tasks);

        // Unmark the task as done
        String s = taskList.unmarkTask(0, ui);

        // Check if the task is marked as not done
        assertFalse(tasks.get(0).isDone());
        assertEquals("OK, I've marked this task as not done yet:\n" +
                "[T][ ] Task 1\n", s);
    }
}