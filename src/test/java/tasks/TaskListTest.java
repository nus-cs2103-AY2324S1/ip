package tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class TaskListTest {
    @Test
    public void testTodoTask_validInput_expectedOutcome() {
        TaskList taskList = new TaskList();
        Task todoTask = taskList.addTodo("some todo", 0);
        assertEquals("[T][ ] some todo", todoTask.toString());
    }

    @Test
    public void testDeadlineTask_validInput_expectedOutcome() {
        TaskList taskList = new TaskList();
        Task deadlineTask = taskList.addDeadline("some deadline",
                "2023-01-01 1500",
                0);
        String expected = "[D][ ] some deadline (by: 01-Jan-2023 1500)";
        assertEquals(expected, deadlineTask.toString());
    }

    @Test
    public void testDeadlineTask_invalidDatetime_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addDeadline("some deadline",
                    "2023-01-01 later",
                    0);
            fail();
        } catch (Exception e) {
            String expected = "Text '2023-01-01 later' could not be parsed at index 11";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testEventTask_validInput_expectedOutcome() {
        TaskList taskList = new TaskList();
        Task eventTask = taskList.addEvent("some event",
                "2023-01-01 1530",
                "2023-01-01 1600",
                0);
        String expected = "[E][ ] some event (from: 01-Jan-2023 1530 to: 01-Jan-2023 1600)";
        assertEquals(expected, eventTask.toString());
    }

    @Test
    public void testEventTask_invalidDatetime_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addEvent("some deadline",
                    "today",
                    "tomorrow",
                    0);
            fail();
        } catch (Exception e) {
            String expected = "Text 'today' could not be parsed at index 0";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testMarkTask_validIndex_expectedOutcome() {
        TaskList taskList = new TaskList();
        Task todoTask = taskList.addTodo("A todo task.", 0);
        assertEquals("[T][ ] A todo task.", todoTask.toString());
        taskList.markTaskAsDone(1);
        assertEquals("[T][X] A todo task.", todoTask.toString());
    }

    @Test
    public void testMarkTask_invalidIndex_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.addTodo("A todo task.", 0);
            taskList.markTaskAsDone(3);
            fail();
        } catch (Exception e) {
            String expected = "Invalid index format.\n"
                    + "Format should be: mark <task index> OR unmark <task index> "
                    + "OR delete <task index>\n"
                    + "where index is from 1 to 1.";
            assertEquals(expected, e.getMessage());
        }
    }

    @Test
    public void testUnmarkTask_validIndex_expectedOutcome() {
        TaskList taskList = new TaskList();
        Task todoTask = taskList.addTodo("Another todo task.", 1);
        assertEquals("[T][X] Another todo task.", todoTask.toString());
        taskList.unmarkTask(1);
        assertEquals("[T][ ] Another todo task.", todoTask.toString());
    }

}
