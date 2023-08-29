package duke;

import exception.DukeException;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void isValidDate_correctInput_Test() {
        assertTrue(Parser.isValidDate("20/11/2000 1800"));
    }

    @Test
    public void isValidDate_invalidInput1_Test() {
        assertFalse(Parser.isValidDate("20-11-2000 1800"));
    }

    @Test
    public void isValidDate_invalidInput2_Test() {
        assertFalse(Parser.isValidDate("20/11/2000"));
    }

    @Test
    public void replyUser_invalidInput_Test() {
        TaskList taskListObj = new TaskList();
        Ui uiObj = new Ui();
        DukeException e = assertThrows(DukeException.class, () -> Parser.replyUser("", taskListObj, uiObj));
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
    }

    @Test
    public void replyUser_invalidInput2_Test() {
        TaskList taskListObj = new TaskList();
        taskListObj.addTask(new Task("buy book"));
        Ui uiObj = new Ui();
        DukeException e = assertThrows(DukeException.class, () -> Parser.replyUser("delete ", taskListObj, uiObj));

        assertEquals("☹ OOPS!!! The number input does not exist.", e.getMessage());
    }

}
