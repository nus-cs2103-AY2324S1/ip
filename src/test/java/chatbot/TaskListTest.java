package chatbot;

import chatbot.exceptions.InvalidTaskIndexException;
import chatbot.tasks.DeadlineTask;
import chatbot.tasks.EventTask;
import chatbot.tasks.ToDoTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class TaskListTest {

    @Test
    public void getSizeTest1() {
        TaskList taskList = new TaskList();
        assertEquals(taskList.getSize(), 0);
    }

    @Test
    public void getSizeTest2() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        taskList.addTask(new DeadlineTask("2", "tmr"));
        taskList.addTask(new EventTask("3", "4", "5"));
        assertEquals(taskList.getSize(), 3);
    }

    @Test
    public void markAsTest1() {
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
    public void markAsTest2() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(true, 1);
            assertEquals("[T][X] 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void markAsTest3() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(false, 1);
            assertEquals("[T][ ] 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

    @Test
    public void markAsTest4() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("1"));
        try {
            taskList.markAs(false, 1);
            taskList.markAs(true, 1);
            assertEquals("[T][X] 1\n", taskList.taskListToStrings());
        } catch (InvalidTaskIndexException e) {
            fail();
        }
    }

}
