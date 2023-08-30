package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import duke.command.MarkCommand;
import org.junit.jupiter.api.Test;

import duke.command.ExitCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.exception.DukeBadInputException;


public class ParserTest {

    @Test
    public void parseCommand_correctInputs() {

        try {
            // bye command
            assertEquals(new ExitCommand(), Parser.parse("bye"));
            // list command
            assertEquals(new ListCommand(), Parser.parse("list"));
            // help command
            assertEquals(new HelpCommand(), Parser.parse("help"));
            // help command
            assertEquals(new MarkCommand(true, 1), Parser.parse("mark 1"));
        } catch (DukeBadInputException e) {
            fail();
        }
    }
}
