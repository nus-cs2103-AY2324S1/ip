package commands;

import duke.DukeException;
import data.Actions;
import parser.Parser;
import org.junit.jupiter.api.Test;
import tasks.Event;
import ui.UI;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventCommandTest {

    @Test
    public void testExecuteCommand_missingByKeyword_throwsDukeException() {
        Parser parse = new Parser();
        UI ui = new UI();
        Actions actions = new Actions();
        EventCommand event = new EventCommand("event task", parse);
        assertThrows(DukeException.class, () -> event.executeCommand(ui, actions));
    }

    @Test
    public void testExecuteCommand_validInput_taskAdded() {
        Parser parse = new Parser();
        UI ui = new UI();
        Actions actions = new Actions();
        String description = "Event Test Task";
        String from = "22/09/2023 0000";
        String to = "22/09/2023 2359";
        EventCommand event = new EventCommand(description + " /from " + from + " /to " + to, parse);
        try {
            event.executeCommand(ui, actions);
        } catch (DukeException thrown) {
            throw new AssertionError("DukeException was thrown with a valid input.", thrown);
        }
    }
}