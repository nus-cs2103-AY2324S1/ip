package mainDuke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import mainDuke.exceptions.DukeException;
import mainDuke.exceptions.TaskParseException;
import mainDuke.task.Deadline;
import mainDuke.task.Task;
import mainDuke.task.Todo;


public class DukeTest {
    @Test
    public void parseTypeTest() {
        try {
            Duke.TaskType result = Parser.parseType("Todo make");
            assertEquals(Duke.TaskType.TODO, result);
        } catch (DukeException e) {
            fail(e.getMessage());
        } catch (TaskParseException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parseTaskTest() {
        try {
            Task expected = new Deadline(false, "deadline return book /by 2019-12-02");
            Task result = Parser.parseTask("deadline return book /by 2019-12-02", Duke.TaskType.DEADLINE);
            assertEquals(expected.toString(), result.toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void markTasFromTaskListTest() {
        try {
            Task expected = new Todo(true, "todo mark");
            TaskList list = new TaskList();
            list.addTask(new Todo(false, "todo mark"));
            list.markTask(0);
            Task result = list.getTask(0);
            assertEquals(result.toString(), expected.toString());
        } catch (DukeException e) {
            fail(e.getMessage());
        }
    }
}
