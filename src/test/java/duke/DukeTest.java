package duke;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DukeTest {

    @Test
    public void testDukeInitialization() {
        Duke duke = new Duke("data/testDuke.txt");
        assertNotNull(duke.getWelcomeMessage());
    }

    @Test
    public void testDukeResponses() {
        Duke duke = new Duke("data/testDuke.txt");

        // Test valid input
        String response = duke.getResponse("list");
        assertNotNull(response);

        // Test invalid input
        response = duke.getResponse("invalid_command");
        assertNotNull(response);
    }

    @Test
    public void testExitCommand() {
        Duke duke = new Duke("data/testDuke.txt");

        // Duke should not exit at the start
        assertFalse(duke.isExit());

        // Duke should exit when exit command is given
        duke.getResponse("bye");
        assertTrue(duke.isExit());
    }
}
