package duke;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void deleteTask_negativeInput_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.deleteTask(-1231);
        } catch (DukeException e) {
            assertEquals("Invalid Task entered. Please try again...", e.getMessage());
        }
    }
    @Test
    public void findTask_itemNotInList_printEmptyListWithUi() {
        TaskList taskList = new TaskList();
        assertEquals("Duke could not find your query! Please try again...", taskList.findTask("//-=./;'#%^2"));
    }
    @Test
    public void deleteTask_emptyList_exceptionThrown() {
        try {
            TaskList taskList = new TaskList();
            taskList.deleteTask(1);
        } catch (DukeException e) {
            assertEquals("Task Scheduler is empty... Please try again!", e.getMessage());
        }
    }
    @Test
    public void deleteTask_singleElementList_success() {
        assertDoesNotThrow(() -> {
            TaskList taskList = new TaskList();
            taskList.addToDo("Dummy event");
            taskList.deleteTask(1);
        });
    }
    @Test
    public void findTask_itemInList_success() {
        assertDoesNotThrow(() -> {
            TaskList taskList = new TaskList();
            taskList.addToDo("Dummy event");
            taskList.findTask("D");
        });
    }
}
