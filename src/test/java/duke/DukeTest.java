package duke;

import duke.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    private Duke duke = new Duke();
    private TaskList taskList = new TaskList();

    @Test
    public void testAddToDo() {
        try {
            duke.addToDo("todo watch videos", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testAddDeadline() {
        try {
            duke.addDeadline("deadline assignment /by 12/03/2040 1345", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testAddEvent() {
        try {
            duke.addEvent("event meeting /from 6th June /to 9th June", taskList);
            assertEquals(1, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }

    @Test
    public void testRemoveTask() {
        try {
            duke.addToDo("todo play!", taskList);

            duke.deleteTask("delete 1", taskList);
            assertEquals(0, taskList.getSize());
        } catch (Exception e) {
            fail("Exception should not be thrown.");
        }
    }
    
}
