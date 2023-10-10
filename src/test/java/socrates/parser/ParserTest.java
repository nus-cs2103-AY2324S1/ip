package socrates.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import socrates.command.AddDeadlineCommand;
import socrates.command.AddEventCommand;
import socrates.command.AddTodoCommand;
import socrates.command.ClearCommand;
import socrates.command.DeleteCommand;
import socrates.command.ExitCommand;
import socrates.command.FindCommand;
import socrates.command.HelpCommand;
import socrates.command.ListCommand;
import socrates.command.MarkCommand;
import socrates.command.UnmarkCommand;
import socrates.data.exception.InvalidDateException;
import socrates.data.exception.InvalidKeywordException;
import socrates.data.exception.InvalidTaskArgumentException;
import socrates.data.exception.InvalidTaskIndexException;
import socrates.data.exception.SocratesException;


public class ParserTest {
    @Test
    public void parse_emptyCommandWord_throwsSocratesException() {
        assertThrows(SocratesException.class, () -> Parser.parse(""));
    }

    @Test
    public void parse_invalidCommandWord_throwsSocratesException() {
        assertThrows(SocratesException.class, () -> Parser.parse("abc"));
    }

    @Test
    public void parse_byeCommandWord_returnsExitCommand() {
        try {
            assertTrue(Parser.parse("bye") instanceof ExitCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyTodoDescription_throwsInvalidTaskArgumentException() {
        try {
            Parser.parse("todo");
        } catch (InvalidTaskArgumentException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Todo Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_validTodo_returnsAddTodoCommand() {
        try {
            assertTrue(Parser.parse("todo desc") instanceof AddTodoCommand);
            assertTrue(Parser.parse("todo desc abc") instanceof AddTodoCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeadlineDescription_throwsInvalidTaskArgumentException() {
        try {
            Parser.parse("deadline");
        } catch (InvalidTaskArgumentException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Deadline Command was successfully constructed with empty description.");
    }

    @Test
    public void parse_emptyDeadlineBy_throwsInvalidTaskArgumentException() {
        try {
            Parser.parse("deadline desc");
        } catch (InvalidTaskArgumentException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Deadline Command was successfully constructed without /by field.");
    }

    @Test
    public void parse_invalidDeadlineBy_throwsInvalidDateException() {
        try {
            Parser.parse("deadline desc /by invalid");
        } catch (InvalidDateException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Deadline Command was successfully constructed with invalid /by field.");
    }

    @Test
    public void parse_validDeadline_returnsAddDeadlineCommand() {
        try {
            assertTrue(Parser.parse("deadline desc /by 2023-01-01") instanceof AddDeadlineCommand);
            assertTrue(Parser.parse("deadline desc /by 1999-12-30") instanceof AddDeadlineCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyEventDescription_throwsInvalidTaskArgumentException() {
        try {
            Parser.parse("event");
        } catch (InvalidTaskArgumentException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Event Command was successfully constructed with empty description.");

    }

    @Test
    public void parse_invalidEventFrom_throwsInvalidDateException() {
        try {
            Parser.parse("event desc /from invalid /to 2023-01-01");
        } catch (InvalidDateException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Event Command was successfully constructed with invalid /from field.");
    }

    @Test
    public void parse_invalidEventTo_throwsInvalidDatetException() {
        try {
            Parser.parse("event desc /from 2023-01-01 /to invalid");
        } catch (InvalidDateException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Add Event Command was successfully constructed with invalid /to field.");
    }

    @Test
    public void parse_validEvent_returnsAddEventCommand() {
        try {
            assertTrue(Parser.parse("event desc /from 2023-01-31 /to 2024-01-01") instanceof AddEventCommand);
            assertTrue(Parser.parse("event desc /from 2020-01-31 /to 2024-01-01") instanceof AddEventCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_listCommand_returnsListCommand() {
        try {
            assertTrue(Parser.parse("list") instanceof ListCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_clearCommand_returnsClearCommand() {
        try {
            assertTrue(Parser.parse("clear") instanceof ClearCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyKeyword_throwsInvalidKeywordException() {
        try {
            Parser.parse("find");
        } catch (InvalidKeywordException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Find Command was successfully constructed with empty keyword.");
    }

    @Test
    public void parse_multipleKeyword_throwsInvalidKeywordException() {
        try {
            Parser.parse("find a b");
        } catch (InvalidKeywordException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Find Command was successfully constructed with multiple keywords.");
    }

    @Test
    public void parse_validKeyword_returnsFindCommand() {
        try {
            assertTrue(Parser.parse("find a") instanceof FindCommand);
            assertTrue(Parser.parse("find abc") instanceof FindCommand);
            assertTrue(Parser.parse("find keyword") instanceof FindCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_helpCommand_returnsHelpCommand() {
        try {
            assertTrue(Parser.parse("help") instanceof HelpCommand);
            assertTrue(Parser.parse("help anything") instanceof HelpCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("mark");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Mark Command was successfully constructed with no index.");
    }

    @Test
    public void parse_invalidMarkIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("mark invalid");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Mark Command was successfully constructed with invalid index.");
    }

    @Test
    public void parse_validMarkIndex_returnsMarkCommand() {
        try {
            assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
            assertTrue(Parser.parse("mark 999") instanceof MarkCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyUnmarkIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("unmark");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Unmark Command was successfully constructed with no index.");
    }

    @Test
    public void parse_invalidUnmarkIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("unmark invalid");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Unmark Command was successfully constructed with invalid index.");
    }

    @Test
    public void parse_validUnmarkIndex_returnsUnmarkCommand() {
        try {
            assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
            assertTrue(Parser.parse("unmark 999") instanceof UnmarkCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeleteIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("delete");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Delete Command was successfully constructed with no index.");
    }

    @Test
    public void parse_invalidDeleteIndex_throwsInvalidTaskIndexException() {
        try {
            Parser.parse("delete invalid");
        } catch (InvalidTaskIndexException e) {
            return;
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
        fail("Delete Command was successfully constructed with invalid index.");
    }

    @Test
    public void parse_validDeleteIndex_returnsUnmarkCommand() {
        try {
            assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
            assertTrue(Parser.parse("delete 999") instanceof DeleteCommand);
        } catch (SocratesException e) {
            fail(e.getMessage());
        }
    }

}
