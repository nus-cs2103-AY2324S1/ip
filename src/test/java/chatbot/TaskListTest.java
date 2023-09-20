package chatbot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.ToDoTask;

/**
 * Test class that performs tests with the task list.
 */
public class TaskListTest {

    @Test
    public void getSize_empty_zero() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
    }

    @Test
    public void getSize_createThree_three() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        taskList.addTask(new DeadlineTask("2", "tmr"));
        taskList.addTask(new EventTask("3", "4", "5"));
        assertEquals(taskList.getSize(), 3);
    }

    @Test
    public void getSize_createThreeDeleteTwo_one() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        taskList.addTask(new DeadlineTask("2", "tmr"));
        taskList.addTask(new EventTask("3", "4", "5"));
        try {
            taskList.deleteTask(1);
            taskList.deleteTask(1);
            assertEquals(taskList.getSize(), 1);
        } catch (InvalidTaskIndexException e) {
            fail();
        }

    }

    @Test
    public void markAs_invalidIndex_exception() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(true, 2);
            fail();
        } catch (InvalidTaskIndexException e) {
            assertTrue(true);
        }
    }

    @Test
    public void markAs_doneTwice_done() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(true, 1);
            taskList.markAs(true, 1);
            assertEquals("[T][X] (L) 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void markAs_unmark_undone() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(false, 1);
            assertEquals("[T][ ] (L) 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void markAs_unmarkThenMark_done() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(false, 1);
            taskList.markAs(true, 1);
            assertEquals("[T][X] (L) 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

}
