package duke.task;
import duke.command.AddCommand;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
public class EventTest {
    @Test
    public void invalidTime_exceptionThrown(){
        try {
            Task task = new Event("event Final Exam /from sunday /to friday");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid input of Date", e.getMessage());
        }
    }

    @Test
    public void invalidStartingTime_exceptionThrown(){
        try {
            Task task = new Event("event Final Exam /from 2023-08-11 /to 2023-08-10");
            fail();
        } catch (DukeException e) {
            assertEquals("The starting time could not pass the ending time", e.getMessage());
        }
    }
    @Test
    public void toString_validDate(){
        try {
            Task task = new Event("event Final Exam /from 2023-08-11 /to 2023-08-13");
            assertEquals("[E] [ ] event Final Exam (from: Aug 11 2023 to: Aug 13 2023)", task.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
