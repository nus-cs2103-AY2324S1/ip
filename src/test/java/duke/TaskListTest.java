package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.tasks.Todo;

/**
 * Test class for the TaskList class.
 * Contains test cases for the markTask method.
 */
//CHECKSTYLE.OFF: MethodName
public class TaskListTest {
    @Test
    public void markTask_markUnmarkedTask_success() {
        // Create an unmarked task
        Todo todo = new Todo("Test Todo", false);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.list.add(todo);

        // Mark the unmarked task
        String result = taskList.markTask(0);

        // Ensure that the task is marked and check the result message
        assertEquals(todo.getIsMarked(), true);
        assertEquals("I HAVE MARKED THIS TASK:[T][X] Test Todo", result);
    }
    @Test
    public void markTask_markTaskAtIndexOutOfBounds_exceptionThrown() {
        try {
            TaskList taskList = new TaskList(new ArrayList<>());
        } catch (Exception e) {
            // Attempt to mark a task at an index that is out of bounds
            assertEquals(e.getMessage(), ("You have provided a number out of index of the stored tasks"));
        }
    }

    @Test
    public void markTask_markTaskAtIndexValid_success() {
        // Create a list of unmarked tasks
        Todo todo1 = new Todo("Task 1", false);
        Todo todo2 = new Todo("Task 2", false);
        Todo todo3 = new Todo("Task 3", false);
        TaskList taskList = new TaskList(new ArrayList<>());
        taskList.list.add(todo1);
        taskList.list.add(todo2);
        taskList.list.add(todo3);

        // Mark the task at a valid index
        String result = taskList.markTask(1);

        // Ensure that the task at index 1 is marked and check the result message
        assertEquals(todo2.getIsMarked(), true);
        assertEquals("I HAVE MARKED THIS TASK:[T][X] Task 2", result);
    }
    //CHECKSTYLE.ON: MethodName
}
