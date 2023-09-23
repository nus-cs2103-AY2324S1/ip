package bob.data.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import bob.data.exception.DukeException;
import bob.parser.Parser;

public class TaskListTest {
    @Test
    public void setTaskComplete_correctMarkTaskInput_taskSetAsComplete() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            assertTrue(testList.setTaskComplete("mark 1").contains("OK, I've marked this task as done:\n"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void setTaskComplete_markTaskInputOutOfRange_exceptionThrown() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            DukeException thrown = assertThrows(DukeException.class, ()
                            -> testList.setTaskComplete("mark 2"),
                    "Expected setTaskComplete to throw but it did not.");
            assertTrue(thrown.getMessage().contains("Task number out of range!"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void setTaskIncomplete_correctUnmarkTaskInput_taskSetAsIncomplete() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            assertTrue(testList.setTaskIncomplete("unmark 1").contains("OK, I've marked this task as not done yet:\n"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void setTaskIncomplete_unmarkTaskInputOutOfRange_exceptionThrown() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            DukeException thrown = assertThrows(DukeException.class, ()
                            -> testList.setTaskComplete("unmark 2"),
                    "Expected setTaskIncomplete to throw but it did not.");
            assertTrue(thrown.getMessage().contains("Task number out of range!"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void deleteTask_correctDeleteTaskInput_taskDeleted() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            assertTrue(testList.deleteTask("delete 1").contains("Noted. I've removed this task:\n"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void deleteTask_deleteTaskInputOutOfRange_exceptionThrown() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            DukeException thrown = assertThrows(DukeException.class, ()
                            -> testList.deleteTask("delete 2"),
                    "Expected deleteTask to throw but it did not.");
            assertTrue(thrown.getMessage().contains("Task number out of range!"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void deleteTaskAtIndex_correctIndex_taskAtIndexDeleted() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            testList.deleteTaskAtIndex(0);
            assertTrue(testList.getSize() == 0);
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void deleteTaskAtIndex_indexOutOfRange_taskAtIndexDeleted() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            DukeException thrown = assertThrows(DukeException.class, ()
                            -> testList.deleteTaskAtIndex(2),
                    "Expected deleteTaskAtIndex to throw but it did not.");
            assertTrue(thrown.getMessage().contains("Task number out of range!"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void testToString() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            testList.addTaskWithCommand(Parser.CommandType.DEADLINE, "deadline test /by 12/12/2024 1200");
            testList.addTaskWithCommand(Parser.CommandType.EVENT,
                    "event test /from 12/12/2024 1200 /to 13/12/2024 1200");
            assertEquals("1.[T][ ] test\n"
                    + "2.[D][ ] test (by: 12 Dec 2024 12:00 PM)\n"
                    + "3.[E][ ] test (from: 12 Dec 2024 12:00 PM to: 13 Dec 2024 12:00 PM)",
                    testList.toString());
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void find_findableInput_taskFound() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            assertEquals("1.[T][ ] test", testList.find("find test"));
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void find_unfindableInput_nothingFound() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            assertTrue(testList.find("find lol").contains("No tasks found matching that description."));
        } catch (DukeException e) {
            System.out.println(e);
            fail("An exception was thrown.");
        }
    }

    @Test
    public void swap_correctIndexes_tasksSwapped() {
        TaskList testList = new TaskList();
        try {
            testList.addTaskWithCommand(Parser.CommandType.TODO, "todo test");
            testList.addTaskWithCommand(Parser.CommandType.DEADLINE, "deadline test /by 12/12/2024 1200");
            testList.addTaskWithCommand(Parser.CommandType.EVENT,
                    "event test /from 12/12/2024 1200 /to 13/12/2024 1200");
            testList.swap(0, 2);
            assertEquals("1.[E][ ] test (from: 12 Dec 2024 12:00 PM to: 13 Dec 2024 12:00 PM)\n"
                            + "2.[D][ ] test (by: 12 Dec 2024 12:00 PM)\n"
                            + "3.[T][ ] test",
                    testList.toString());
        } catch (DukeException e) {
            System.out.println(e);
            fail("Expected exception not thrown.");
        }
    }

    @Test
    public void swap_indexesOutOfRange_exceptionThrown() {
        TaskList testList = new TaskList();
        DukeException thrown = assertThrows(DukeException.class, () -> testList.swap(-1, 2),
                "Expected swap to throw but it did not.");
        assertTrue(thrown.getMessage().contains("task number out of range!"));
    }
}
