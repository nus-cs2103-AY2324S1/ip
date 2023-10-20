package seedu.duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.duke.command.AddCommand;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.ErrorCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.MarkCommand;
import seedu.duke.command.UnmarkCommand;

public class ParserTest {

    @Test
    public void testParseUserInput_intoCommandsObject_bye() {
        assertEquals(true, new Parser().parse("bye") instanceof ExitCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_list() {
        assertEquals(true, new Parser().parse("list") instanceof ListCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_mark() {
        assertEquals(true, new Parser().parse("mark") instanceof MarkCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_unmark() {
        assertEquals(true, new Parser().parse("unmark") instanceof UnmarkCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_delete() {
        assertEquals(true, new Parser().parse("delete") instanceof DeleteCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_todo() {
        assertEquals(true, new Parser().parse("todo") instanceof AddCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_deadline() {
        assertEquals(true, new Parser().parse("deadline") instanceof AddCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_event() {
        assertEquals(true, new Parser().parse("event") instanceof AddCommand);
    }

    @Test
    public void testParseUserInput_intoCommandsObject_default() {
        assertEquals(true, new Parser().parse("") instanceof ErrorCommand);
        assertEquals(true, new Parser().parse("asdf") instanceof ErrorCommand);
        assertEquals(true, new Parser().parse(" ") instanceof ErrorCommand);
    }

}
