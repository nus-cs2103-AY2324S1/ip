package chatbuddy.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import chatbuddy.ChatBuddyException;
import chatbuddy.command.Command;
import chatbuddy.command.DeadlineCommand;
import chatbuddy.command.DeleteCommand;
import chatbuddy.command.EventCommand;
import chatbuddy.command.ExitCommand;
import chatbuddy.command.FindCommand;
import chatbuddy.command.ListCommand;
import chatbuddy.command.MarkCommand;
import chatbuddy.command.TagCommand;
import chatbuddy.command.TodoCommand;
import chatbuddy.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_listCommandWord_listCommandReturned() {
        try {
            Command command = Parser.parse("list");
            assertInstanceOf(ListCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }

        try {
            Command command = Parser.parse("list abcdefg");
            assertInstanceOf(ListCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_exitCommandWord_exitCommandReturned() {
        try {
            Command command = Parser.parse("bye");
            assertInstanceOf(ExitCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_todoCommandWord_todoCommandReturned() {
        try {
            Command command = Parser.parse("todo hw");
            assertInstanceOf(TodoCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_deadlineCommandWord_deadlineCommandReturned() {
        try {
            Command command = Parser.parse("deadline do homework /by 02/09/2023");
            assertInstanceOf(DeadlineCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_deleteCommandWord_deleteCommandReturned() {
        try {
            Command command = Parser.parse("delete 1");
            assertInstanceOf(DeleteCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_markCommandWord_markCommandReturned() {
        try {
            Command command = Parser.parse("mark 1");
            assertInstanceOf(MarkCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_unmarkCommandWord_unmarkCommandReturned() {
        try {
            Command command = Parser.parse("unmark 1");
            assertInstanceOf(UnmarkCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_findCommandWord_findCommandReturned() {
        try {
            Command command = Parser.parse("find hw");
            assertInstanceOf(FindCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_tagCommandWord_tagCommandReturned() {
        try {
            Command command = Parser.parse("tag 1 urgent");
            assertInstanceOf(TagCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }

        try {
            Command command = Parser.parse("tag 1");
            assertInstanceOf(TagCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_eventCommandWord_eventCommandReturned() {
        try {
            Command command = Parser.parse(
                    "event birthday party /from 03/09/2023 1400 /to 03/09/2023 1700"
            );
            assertInstanceOf(EventCommand.class, command);
        } catch (ChatBuddyException e) {
            fail();
        }
    }

    @Test
    public void parse_invalidCommandWord_chatBuddyExceptionThrown() {
        try {
            Parser.parse("hi");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            Parser.parse("Bye");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_missingTodoDescription_chatBuddyExceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("todo      ");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineInput_chatBuddyExceptionThrown() {
        String errorMessage = "OOPS!!! Please input deadlines in the format "
                + "'deadline [task description] /by [deadline in dd/MM/yyyy]'.\n"
                + "The task description and deadline cannot be empty.";

        String dateFormatErrorMessage = "OOPS!!! Please input the deadline date in the format dd/MM/yyyy.";

        try {
            Parser.parse("deadline    ");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Parser.parse("deadline /by");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Parser.parse("deadline xx /by    ");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals(errorMessage, e.getMessage());
        }

        try {
            Parser.parse("deadline task /by Sunday");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals(dateFormatErrorMessage, e.getMessage());
        }

        try {
            Parser.parse("deadline task /by 01/09/2023 2359");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals(dateFormatErrorMessage, e.getMessage());
        }
    }
}
