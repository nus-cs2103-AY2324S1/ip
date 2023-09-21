package commands;

import duke.DukeException;
import data.Actions;
import parser.Parser;
import org.junit.jupiter.api.Test;
import tasks.Deadline;
import ui.UI;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {

    @Test
    public void testExecuteCommand_missingByKeyword_throwsDukeException() {
        Parser parse = new Parser();
        UI ui = new UI();
        Actions actions = new Actions();
        DeadlineCommand deadline = new DeadlineCommand("deadline task", parse);
        assertThrows(DukeException.class, () -> deadline.executeCommand(ui, actions));
    }

    @Test
    public void testExecuteCommand_validInput_taskAdded() {
        Parser parse = new Parser();
        UI ui = new UI();
        Actions actions = new Actions();
        String description = "Deadline Test Task";
        String time = "22/09/2023 2359";
        DeadlineCommand deadline = new DeadlineCommand(description + " /by " + time, parse);
        try {
            deadline.executeCommand(ui, actions);
        } catch (DukeException thrown) {
            throw new AssertionError("DukeException was thrown with a valid input.", thrown);
        }
    }
}