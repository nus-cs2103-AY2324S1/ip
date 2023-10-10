package commands;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import data.Actions;
import data.Save;
import duke.DukeException;
import parser.Parser;
import ui.UI;

public class EventCommandTest {

    @Test
    public void missingKeyword() {
        Save dummySave = new Save();
        Parser parse = new Parser(dummySave);
        UI ui = new UI();
        Actions actions = new Actions();
        EventCommand event = new EventCommand("event task", parse);
        assertThrows(DukeException.class, () -> event.executeCommand(ui, actions));
    }

    @Test
    public void taskAdded() {
        Save dummySave = new Save();
        Parser parse = new Parser(dummySave);
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
