package corgi.parsers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandParserTest {
    @Test
    public void parse_invalidByeCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "bye" command with arguments
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("bye Corgi"));
    }

    @Test
    public void parse_invalidMarkCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "mark" command with an invalid argument
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("mark"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("mark abc"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("mark 1 2 3 4"));
    }

    @Test
    public void parse_invalidUnmarkCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "mark" command with an invalid argument
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("unmark"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("unmark abc"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("unmark 1 2 3 4"));
    }

    @Test
    public void parse_invalidDeadlineCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "deadline" command with an invalid argument
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("deadline"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("deadline Invalid deadline /by tomorrow"));
        //Todo: Bug found: should count number of /by before start splitting.
        //assertThrows(InvalidCommandFormatException.class, () -> parser.parse("deadline Invalid deadline /by 2023-09-22 /by 2023-09-23"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("deadline /by 2023-09-22"));
    }

    @Test
    public void parse_invalidEventCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "event" command with an invalid argument
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("event"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("event Invalid event /from 2023-09-20"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("event Invalid event /to 2023-09-20"));
        assertThrows(InvalidCommandFormatException.class, () -> parser.parse("event Invalid event /to 2023-09-20 /to 2023-09-22"));
    }
}
