package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.task.Task;
import duke.task.Todo;

public class TaskListTest {
    @Test
    public void deleteTask_validIndexNumber_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("nothing", false);
        try {
            taskList.addTask(todo);
            taskList.delete(1);
            assertEquals(0, taskList.getSize());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
    @Test
    public void deleteTask_invalidIndexNumber_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.delete(1);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! There is no task with this index number.", e.getMessage());
        }
    }

    @Test
    public void getTask_validIndexNumber_success() {
        TaskList taskList = new TaskList();
        Todo todo = new Todo("nothing", false);
        try {
            taskList.addTask(todo);
            Task task = taskList.getTask(1);
            assertEquals(task.equals(todo), true);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    @Test
    public void getTask_invalidIndexNumber_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.getTask(2);
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! There is no task with this index number.", e.getMessage());
        }
    }
}
