package duke;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.SortCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidFormatException;

class ParserTest {

    @Test
    void parse_validTodoInput_returnAddCommand() throws DukeException {
        String input = "todo read a book";
        assertTrue(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    void parse_emptyTodoInput_throwEmptyDescriptionException() {
        String input = "todo";
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_validDeadlineInput_returnAddCommand() throws DukeException {
        String input = "deadline submit assignment /by 2023-09-19 1800";
        assertTrue(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    void parse_invalidDeadlineInput_throwInvalidFormatException() {
        String input = "deadline submit assignment";
        assertThrows(InvalidFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_validEventInput_returnAddCommand() throws DukeException {
        String input = "event team meeting /from 2023-09-19 1400 /to 2023-09-19 1600";
        assertTrue(Parser.parse(input) instanceof AddCommand);
    }

    @Test
    void parse_unknownCommand_throwInvalidCommandException() {
        String input = "unknown command";
        assertThrows(InvalidCommandException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_listCommand_returnListCommand() throws DukeException {
        String input = "list";
        assertTrue(Parser.parse(input) instanceof ListCommand);
    }

    @Test
    void parse_validDeleteCommand_returnDeleteCommand() throws DukeException {
        String input = "delete 2";
        assertTrue(Parser.parse(input) instanceof DeleteCommand);
    }

    @Test
    void parse_invalidDeleteCommand_throwInvalidFormatException() {
        String input = "delete";
        assertThrows(InvalidFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_validMarkCommand_returnMarkCommand() throws DukeException {
        String input = "mark 2";
        assertTrue(Parser.parse(input) instanceof MarkCommand);
    }

    @Test
    void parse_invalidMarkCommand_throwInvalidFormatException() {
        String input = "mark";
        assertThrows(InvalidFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_validUnmarkCommand_returnUnmarkCommand() throws DukeException {
        String input = "unmark 2";
        assertTrue(Parser.parse(input) instanceof UnmarkCommand);
    }

    @Test
    void parse_invalidUnmarkCommand_throwInvalidFormatException() {
        String input = "unmark";
        assertThrows(InvalidFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_validFindCommand_returnFindCommand() throws DukeException {
        String input = "find book";
        assertTrue(Parser.parse(input) instanceof FindCommand);
    }

    @Test
    void parse_invalidFindCommand_throwInvalidFormatException() {
        String input = "find";
        assertThrows(InvalidFormatException.class, () -> Parser.parse(input));
    }

    @Test
    void parse_sortCommand_returnSortCommand() throws DukeException {
        String input = "sort";
        assertTrue(Parser.parse(input) instanceof SortCommand);
    }

    @Test
    void parse_byeCommand_returnExitCommand() throws DukeException {
        String input = "bye";
        assertTrue(Parser.parse(input) instanceof ExitCommand);
    }
}
