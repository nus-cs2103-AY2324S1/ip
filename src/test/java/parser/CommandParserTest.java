package parser;

import command.DeleteCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import enums.Command;
import exception.InvalidCommandException;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandParserTest {
    @Test
    public void testBlankSpaces() {
        assertThrows(MissingArgumentException.class, () -> CommandParser.getCommandArguments("mark      ", Command.MARK));
    }
    @Test
    public void testMispeltCommand() {
        assertThrows(InvalidCommandException.class, () -> CommandParser.getCommandArguments("markus", Command.MARK));
    }
    @Test
    public void validMarkInput() {
        try {
            String output = CommandParser.getCommandArguments("mark 1", Command.MARK);
            assertEquals("1", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    @Test
    public void validDeleteInput() {
        try {
            String output = CommandParser.getCommandArguments("delete 1", Command.UNMARK);
            assertEquals("1", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }
    @Test
    public void validUnmarkInput() {
        try {
            String output = CommandParser.getCommandArguments("unmark 12", Command.UNMARK);
            assertEquals("12", output);
        } catch (Exception e) {
            Assert.fail();
        }
    }
}
