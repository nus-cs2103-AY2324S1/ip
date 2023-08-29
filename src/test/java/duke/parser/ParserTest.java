package duke.parser;

import duke.commands.AddCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.ExitCommand;
import duke.commands.Command;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    public void list_incorrectCommand() {
        Command c = Parser.parse("list xx");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    public void list_correctCommand() {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void exit_incorrectCommand() {
        Command c = Parser.parse("bye ");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void exit_correctCommand() {
        Command c = Parser.parse("bye");
        assertTrue(c instanceof ExitCommand);
    }

    @Test
    void todo_invalidTask_incorrectCommand() {
        Command c = Parser.parse("todo ");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void todo_validTask_correctCommand() {
        Command c = Parser.parse("todo xxx");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void deadline_validTaskAndInvalidDate_correctCommand() {
        Command c = Parser.parse("deadline xxx /by ");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void deadline_invalidDeadline_incorrectCommand() {
        Command c = Parser.parse("deadline ");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void deadline_missingTaskAndValidDate_incorrectCommand() {
        Command c = Parser.parse("deadline  /by 21/06/02 1200");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void deadline_validTaskAndInvalidDate_incorrectCommand() {
        Command c = Parser.parse("deadline xx /by lorem");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void deadline_validDeadline_correctCommand() {
        Command c = Parser.parse("deadline xx /by 21/08/02 1900");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void event_invalidEvent_incorrectCommand() {
        Command c = Parser.parse("event");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void event_validTaskAndInvalidDate_incorrectCommand() {
        Command c = Parser.parse("event xx");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void event_validTaskAndInvalidTo_incorrectCommand() {
        Command c = Parser.parse("event xx /from 21/05/02 1100");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void event_validEvent_correctCommand() {
        Command c = Parser.parse("event xx /from 21/05/02 1100 /to 21/05/02 1200");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void event_invalidTaskAndValidDates_incorrectCommand() {
        Command c = Parser.parse("event /from 21/05/02 1100 /to 21/05/02 1200");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void event_validTaskAndInvalidFromAndInvalidTo_incorrectCommand() {
        Command c = Parser.parse("event meet friends /from xx /to yy");
        assertTrue(c instanceof IncorrectCommand);
    }

    @Test
    void event_validTaskAndInvalidRange_incorrectCommand() {
        Command c = Parser.parse("event meet friends /from 21/05/02 1100 /to 21/05/02 1000");
        assertTrue(c instanceof IncorrectCommand);
    }

}
