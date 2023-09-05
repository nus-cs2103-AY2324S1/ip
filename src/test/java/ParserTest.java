import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import duke.Parser;
import duke.DukeException;
import duke.Ui;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TaskList;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParseDeadline() {
        try {
            // Create a test input
            String input = "deadline Finish project by: 2023-12-31";
            // Call the parse method and assert the expected output
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            Parser.parse(input, taskList, ui);
            DeadlineTask task = (DeadlineTask) taskList.getTask(0);
            assertEquals("Finish project", task.getDescription());
            assertEquals("2023-12-31", task.getByDate().toString());
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }

    @Test
    public void testParseEvent() {
        try {
            // Create a test input
            String input = "event Birthday party from: 2023-09-01 to: 2023-09-03";
            // Call the parse method and assert the expected output
            Ui ui = new Ui();
            TaskList taskList = new TaskList();
            Parser.parse(input, taskList, ui);
            EventTask task = (EventTask) taskList.getTask(0);
            assertEquals("Birthday party", task.getDescription());
            assertEquals("2023-09-01", task.getFromDate().toString());
            assertEquals("2023-09-03", task.getToDate().toString());
        } catch (DukeException e) {
            fail("Exception should not be thrown");
        }
    }
}


