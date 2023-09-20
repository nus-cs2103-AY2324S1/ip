package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import java.util.ArrayList;

class UiTest {
    private Ui ui;

    @BeforeEach
    public void setUp() {
        ui = new Ui();
    }

    @Test
    public void testGreet() {
        String expected = "I extend to you my utmost felicitations, User! I am Bartholomew Hamish Montgomery."
                + "\n" + "What may I do for you?";
        assertEquals(expected, ui.greet());
    }

    @Test
    public void testGoodbye() {
        String expected = "Until we meet once more in the near future, I bid you farewell.";
        assertEquals(expected, ui.goodbye());
    }

    @Test
    public void testFormatText() {
        String inputText = "Hello, World!";
        String expected = "_______________________________________\nHello, World!\n"
                + "_______________________________________\n";
        assertEquals(expected, ui.formatText(inputText));
    }

    @Test
    public void testGetAddTaskMessage() {
        Task task = new ToDo("Test todo");
        int numTasks = 5;
        String expected = "Got it! I've added this task:"
                + "\n" + "[T][ ] Test todo" + "\n"
                + "You now have 5 task(s) in the list";
        assertEquals(expected, ui.getAddTaskMessage(task, numTasks));
    }

    @Test
    public void testGetInvalidDateMessage() {
        String expected = "Invalid Date Format! Follow: YYYY-MM-DD";
        assertEquals(expected, ui.getInvalidDateMessage());
    }

    @Test
    public void testGetDeleteTaskMessage() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("Task 1"));
        tasks.add(new ToDo("Task 2"));
        int taskIndex = 1;
        int remainingTasks = 1;
        String expected = "Got it! I've removed this task:"
                + "\n" + "[T][ ] Task 2" + "\n"
                + "You now have 1 task(s) in the list";
        assertEquals(expected, ui.getDeleteTaskMessage(tasks, taskIndex, remainingTasks));
    }

    @Test
    public void testGetMarkedMessage() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo test = new ToDo("Task 1");
        test.mark();
        tasks.add(test);
        int taskIndex = 0;
        String expected = "Great job! You've completed the following task:"
                + "\n" + "[T][X] Task 1";
        assertEquals(expected, ui.getMarkedMessage(tasks, taskIndex));
    }

    @Test
    public void testGetUnmarkedMessage() {
        ArrayList<Task> tasks = new ArrayList<>();
        ToDo test = new ToDo("Task 1");
        test.mark();
        test.unMark();
        tasks.add(test);
        int taskIndex = 0;
        String expected = "You've marked the following task as incomplete:"
                + "\n" + "[T][ ] Task 1";
        assertEquals(expected, ui.getUnmarkedMessage(tasks, taskIndex));
    }

    @Test
    public void testGetSortedMessage() {
        String expected = "Okay, I've sorted your list! \n";
        assertEquals(expected, ui.getSortedMessage());
    }
}


