package corgi.parsers;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class CommandParserTest {

    @Test
    public void parse_invalidByeCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "bye" command with arguments
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("bye Corgi"));
    }

    @Test
    public void parse_invalidListCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "bye" command with arguments
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("list tasks"));
    }

    @Test
    public void parse_invalidUndoCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // Test the parsing of the "bye" command with arguments
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("undo action"));
    }

    @Test
    public void parse_invalidMarkCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark"));
        // Invalid task number -> Non integer
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark /target abc"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark /target 1.5"));
        // Invalid task number -> Multipler tasks
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark /target 1 2 3 4"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark /target 1 /target 2"));
        // Missing argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("mark 1"));
    }

    @Test
    public void parse_invalidUnmarkCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark /target"));
        // Invalid target task number -> non integer
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark /target abc"));
        // Invalid target task number -> multiple numbers
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark /target 1 2 3 4"));
        // Invalid number of argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark /target 1 /target 2"));
        // Missing argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("unmark 1"));
    }

    @Test
    public void parse_invalidDeleteCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete /target"));
        // Invalid target task number -> non integer
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete /target abc"));
        // Invalid target task number -> multiple numbers
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete /target 1 2 3 4"));
        // Invalid number of argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete /target 1 /target 2"));
        // Missing argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("delete 1"));
    }

    @Test
    public void parse_invalidFindCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("find"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("find /target"));
        // Invalid number of argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("find /target pen /target book"));
        // Missing argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("find pen"));
    }

    @Test
    public void parse_invalidDateCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("date"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("date /target"));
        // Invalid number of argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("date /target 2023-09-22 /target 2023-09-23"));
        // Missing argument flag
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("date 2023-09-22"));
        // Invalid date format
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("date tmr"));
    }

    @Test
    public void parse_invalidTodoCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("todo"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("todo /desc"));
        // Invalid number of same argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("todo /desc task1 /desc hard /desc urgent"));
    }


    @Test
    public void parse_invalidDeadlineCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline"));
        // Invalid date format
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc task1 /by tomorrow"));
        // Missing argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /by 2023-09-22"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc task1 2023-09-22"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc /by"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc task1 /by"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc /by 2023-09-22"));
        // Invalid number of same argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("deadline /desc task1 /by 2023-09-22 /by 2023-09-23"));
    }

    @Test
    public void parse_invalidEventCommand_throwsInvalidCommandFormatException() {
        CommandParser parser = new CommandParser();

        // No argument provided
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event"));
        // Missing argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from 2023-09-20"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /to 2023-09-20"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /from 2023-09-10 /to 2023-09-20"));
        // Invalid number of arguments
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from 2023-09-10 /to 2023-09-20 /to 2023-09-22"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /desc event1 /from 2023-09-10 /to 2023-09-22"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from 2023-09-10 /from 2023-09-10 /to 2023-09-22"));
        // Missing value for argument
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from /to 2023-09-22"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc /from 2023-09-10 /from 2023-09-10 /to 2023-09-22"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from 2023-09-10 /from 2023-09-10 /to"));
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc /from /to"));
        // Invalid start date and end date -> start date not before end date
        assertThrows(InvalidCommandFormatException.class, () ->
                parser.parse("event /desc event1 /from 2023-09-24 /to 2023-09-22"));
    }
}
