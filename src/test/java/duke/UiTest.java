package duke;

import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    /**
     * This JUnit test method verifies the correctness of the 'greet' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'greet' method returns the expected greeting message.
     */
    @Test
    public void testGreet() {
        String expected = "---------------------------------------------\n"
                + " Hello! I'm Doraemon\n" + " What can I do for you?\n"
                + "---------------------------------------------";
        String actual = Ui.greet();
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'goodbye' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'goodbye' method returns the expected goodbye message.
     */
    @Test
    public void testGoodbye() {
        String expected = "---------------------------------------------\n"
                + " Bye. Hope to see you again soon!\n"
                + "---------------------------------------------";
        String actual = Ui.goodbye();
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'printLine' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'printLine' method returns the expected horizontal line separator.
     */
    @Test
    public void testPrintLine() {
        String expected = "---------------------------------------------";
        String actual = Ui.printLine();
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getTaskMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getTaskMessage' method returns the expected task message.
     */
    @Test
    public void testGetTaskMessage() {
        Task task = new Task("Sample Task");
        String expected = "   [ ] Sample Task\n";
        String actual = Ui.getTaskMessage(task);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getMarkMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getMarkMessage' method returns the expected mark message.
     */
    @Test
    public void testGetMarkMessage() {
        Task task = new Task("Sample Task", true);
        String expected = " Nice! I've marked this task as done:\n   [X] Sample Task\n";
        String actual = Ui.getMarkMessage(task);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getUnmarkMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getUnmarkMessage' method returns the expected unmark message.
     */
    @Test
    public void testGetUnmarkMessage() {
        Task task = new Task("Sample Task");
        String expected = " OK, I've marked this task as not done yet:\n   [ ] Sample Task\n";
        String actual = Ui.getUnmarkMessage(task);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getAddTaskMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getAddTaskMessage' method returns the expected add task message.
     */
    @Test
    public void testGetAddTaskMessage() {
        TaskList tasks = new TaskList();
        Task task = new Task("Sample Task");
        tasks.addTask(task);
        String expected = " Got it. I've added this task:\n   [ ] Sample Task\n Now you have 1 tasks in the list.";
        String actual = Ui.getAddTaskMessage(tasks, task);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getDeleteMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getDeleteMessage' method returns the expected delete message.
     */
    @Test
    public void testGetDeleteMessage() {
        Task task = new Task("Sample Task");
        String expected = " Noted. I've removed this task:\n   [ ] Sample Task\n";
        String actual = Ui.getDeleteMessage(task);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getFindMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getFindMessage' method returns the expected find message.
     */
    @Test
    public void testGetFindMessage() {
        TaskList tasks = new TaskList();
        tasks.addTask(new Task("Task 1"));
        tasks.addTask(new Task("Task 2"));
        String expected = " Here are the matching tasks in your list:\n 1.[ ] Task 1\n 2.[ ] Task 2\n";
        String actual = Ui.getFindMessage(tasks);
        assertEquals(expected, actual);
    }

    /**
     * This JUnit test method verifies the correctness of the 'getUnknownCommandMessage' method in the 'Ui' class.
     * <p>
     * This test ensures that the 'getUnknownCommandMessage' method returns the expected unknown command message.
     */
    @Test
    public void testGetUnknownCommandMessage() {
        String expected = " OOPS!!! I'm sorry, but I don't know what that means :-(";
        String actual = Ui.getUnknownCommandMessage();
        assertEquals(expected, actual);
    }
}
