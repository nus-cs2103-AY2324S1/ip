package duke.command;

import duke.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void invalidCommand() {
       assertThrows(DukeException.class, () -> {
            Parser.parseCommand("thisIsInvalid");
        });
    }

    @Test
    public void byeReturnsByeCommand() {
        try {
            assertTrue(Parser.parseCommand("bye") instanceof ByeCommand);
        } catch (DukeException ignored) {

        }

    }

    @Test
    public void listReturnsListCommand() {
        try {
            assertTrue(Parser.parseCommand("list") instanceof ListCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void markReturnsMarkCommand() {
        try {
            assertTrue(Parser.parseCommand("mark 1") instanceof MarkCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void unmarkReturnUnmarkCommand() {
        try {
            assertTrue(Parser.parseCommand("unmark 1") instanceof UnmarkCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void deleteReturnDeleteCommand() {
        try {
            assertTrue(Parser.parseCommand("delete 1") instanceof DeleteCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void todoReturnTodoCommand() {
        try {
            assertTrue(Parser.parseCommand("todo") instanceof TodoCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void deadlineReturnDeadlineCommand() {
        try {
            assertTrue(Parser.parseCommand("deadline") instanceof DeadlineCommand);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void eventReturEventCommand() {
        try {
            assertTrue(Parser.parseCommand("event") instanceof EventCommand);
        } catch (DukeException ignored) {

        }
    }
}
