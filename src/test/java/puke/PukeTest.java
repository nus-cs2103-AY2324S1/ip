package puke;

import org.junit.jupiter.api.Test;
import puke.command.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PukeTest {

    @Test
    public void ParserTests() throws PukeException {
        //Testing Parsing ExitCommand
        assertEquals(new ExitCommand(""), Parser.parse("bye", ""));
        //Testing Parsing ListCommand
        assertEquals(new ListCommand(""), Parser.parse("list", ""));
        //Testing Parsing MarkCommand
        assertEquals(new MarkCommand("3"), Parser.parse("mark", " 3"));
        //Testing Parsing UnmarkCommand
        assertEquals(new UnmarkCommand("4"), Parser.parse("unmark", " 4"));
        //Testing Parsing TodoCommand
        assertEquals(new TodoCommand("borrow book"), Parser.parse("todo", " borrow book"));
        //Testing Parsing DeadlineCommand
        assertEquals(new DeadlineCommand("return book /by 2019-12-01T10:00"), Parser.parse("deadline", " return book /by 2019-12-01T10:00"));
        //Testing Parsing EventCommand
        assertEquals(new EventCommand("project meeting /from 2023-08-30T14:00 /to 2023-08-30T16:00"), Parser.parse("event", " project meeting /from 2023-08-30T14:00 /to 2023-08-30T16:00"));
        //Testing Parsing DeleteCommand
        assertEquals(new DeleteCommand("5"), Parser.parse("delete", " 5"));
        //Testing Parsing ClearCommand
        assertEquals(new ClearCommand(""), Parser.parse("clearall", ""));
        //Testing Parsing ErrorCommand 1
        //assertEquals(new ErrorCommand(), Parser.parse("bye", "123"));
        //Testing Parsing ErrorCommand 2
        assertEquals(new ErrorCommand(), Parser.parse("todo", ""));
    }

    @Test
    public void UiMessageTests() {
        //Testing of Ui Exit Message
        assertEquals("It appears that the user has decided to close the program as indicated by the command of which this is the function being issued and therefore,\n" +
                "I shall bid thee farewell and wish thee great fortune in your future endeavors.", new Ui().exit());
    }
}