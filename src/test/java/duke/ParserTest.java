package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.helper.DukeException;
import duke.helper.Parser;

public class ParserTest {
    @Test
    public void todoTest() throws DukeException {
        String input = "todo task 1";
        String command = Parser.parseTest(input);
        assertEquals(command, "TODO");
    }

    @Test
    public void deadlineTest() throws DukeException {
        String input = "deadline task 2 /by 2023-01-01";
        String command = Parser.parseTest(input);
        assertEquals(command, "DEADLINE");
    }
}
