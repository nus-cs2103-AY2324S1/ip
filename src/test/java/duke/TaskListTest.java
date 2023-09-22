package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class TaskListTest {

    @Test
    public void deleteTask_negativeInput_exceptionThrown() {
        try {
            TaskList.deleteTask(-1231);
        } catch (DukeException e) {
            assertEquals("Invalid Task entered. Please try again...", e.getMessage());
        }
    }
    @Test
    public void findTask_itemNotInList_printEmptyListWithUi() {
        assertEquals("Duke could not find your query! Please try again...", TaskList.findTask("//-=./;'#%^2"));
    }
}
