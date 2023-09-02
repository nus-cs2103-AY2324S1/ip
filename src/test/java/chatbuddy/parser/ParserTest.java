package chatbuddy.parser;

import chatbuddy.ChatBuddyException;
import chatbuddy.command.Command;

import chatbuddy.command.ListCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parse_listCommandWord_listCommandReturned() {
        try {
            Command command = Parser.parse("list");
            assertEquals(ListCommand.class, command.getClass());
        } catch (ChatBuddyException e) {
            fail();
        }

        try {
            Command command = Parser.parse("list abcdefg");
            assertEquals(ListCommand.class, command.getClass());
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
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }

        try {
            Parser.parse("Bye");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_missingTodoDescription_chatBuddyExceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }

        try {
            Parser.parse("todo      ");
            fail();
        } catch (ChatBuddyException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_invalidDeadlineInput_chatBuddyExceptionThrown() {
        String errorMessage = "☹ OOPS!!! Please input deadlines in the format " +
                    "'deadline [task description] /by [deadline in dd/MM/yyyy]'.\n" +
                    "The task description and deadline cannot be empty.";

        String dateFormatErrorMessage = "☹ OOPS!!! Please input the deadline date in the format dd/MM/yyyy.";

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
