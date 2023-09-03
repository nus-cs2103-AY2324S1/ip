package bob.parser;

import bob.command.AddCommand;
import bob.command.Command;
import bob.command.DeleteCommand;
import bob.command.ExitCommand;
import bob.command.FindCommand;
import bob.command.ListCommand;
import bob.command.MarkCommand;
import bob.exception.BobException;
import bob.exception.BobInvalidCommandException;
import bob.exception.BobInvalidTaskNumberException;
import bob.exception.BobMissingArgumentException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parse_bye_success() {
        try {
            Command c = Parser.parse("bye");
            assertTrue(c instanceof ExitCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_list_success() {
        try {
            Command c = Parser.parse("list");
            assertTrue(c instanceof ListCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_mark_success() {
        try {
            Command c = Parser.parse("mark 1");
            assertTrue(c instanceof MarkCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_unmark_success() {
        try {
            Command c = Parser.parse("unmark 3");
            assertTrue(c instanceof MarkCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_todo_success() {
        try {
            Command c = Parser.parse("todo learn origami");
            assertTrue(c instanceof AddCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_deadline_success() {
        try {
            Command c = Parser.parse("deadline make origami /by 2023-09-10");
            assertTrue(c instanceof AddCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_event_success() {
        try {
            Command c = Parser.parse("event summer fiesta /from 2023-09-10 /to /2023-09-12");
            assertTrue(c instanceof AddCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_find_success() {
        try {
            Command c = Parser.parse("find book");
            assertTrue(c instanceof FindCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_delete_success() {
        try {
            Command c = Parser.parse("delete 2");
            assertTrue(c instanceof DeleteCommand);
        } catch (BobException e) {
            fail();
        }
    }

    @Test
    public void parse_mark_exceptionThrown1() {
        try {
            Command c = Parser.parse("mark");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
            assertEquals("Give me a task number to mark/unmark as done!", e.getMessage());
        }
    }

    @Test
    public void parse_mark_exceptionThrown2() {
        try {
            Command c = Parser.parse("mark egg sandwich");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
            assertEquals("The mark/unmark command needs to be followed up by an integer number!\n", e.getMessage());
        }
    }

    @Test
    public void parse_unmark_exceptionThrown1() {
        try {
            Command c = Parser.parse("unmark");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
            assertEquals("Give me a task number to mark/unmark as done!", e.getMessage());
        }
    }

    @Test
    public void parse_unmark_exceptionThrown2() {
        try {
            Command c = Parser.parse("unmark test");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
            assertEquals("The mark/unmark command needs to be followed up by an integer number!\n", e.getMessage());
        }
    }

    @Test
    public void parse_todo_exceptionThrown() {
        try {
            Command c = Parser.parse("todo");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobMissingArgumentException);
            assertEquals("The description of your todo should not be empty! Try:\ntodo [description]", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_exceptionThrown() {
        try {
            Command c = Parser.parse("deadline /by");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidCommandException);
            assertEquals("Incorrect deadline command format! It should be:\n"
                    + "deadline [description] /by [duedate]", e.getMessage());
        }
    }

    @Test
    public void parse_event_exceptionThrown() {
        try {
            Command c = Parser.parse("event indonesia trip /from Monday");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidCommandException);
            assertEquals("Incorrect event command format! It should be:\n"
                    + "event [description] /from [start] /to [end]", e.getMessage());
        }
    }

    @Test
    public void parse_delete_exceptionThrown() {
        try {
            Command c = Parser.parse("delete non-integer");
            fail();
        } catch (BobException e) {
            assertTrue(e instanceof BobInvalidTaskNumberException);
            assertEquals("The delete command needs to be followed up by an integer number!\n", e.getMessage());
        }
    }
}
