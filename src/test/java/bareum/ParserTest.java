package bareum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import bareum.commands.Command;
import bareum.commands.AddDeadlineCommand;
import bareum.commands.AddEventCommand;
import bareum.commands.AddTodoCommand;
import bareum.commands.ByeCommand;
import bareum.commands.DeleteCommand;
import bareum.commands.FindCommand;
import bareum.commands.IncorrectCommand;
import bareum.commands.ListCommand;
import bareum.commands.MarkCommand;
import bareum.commands.TagCommand;
import bareum.commands.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_bye_success() throws BareumException {
        Command cmd = Parser.parse("bye");
        assertEquals(ByeCommand.class, cmd.getClass());
    }

    @Test
    public void parse_list_success() throws BareumException {
        Command cmd = Parser.parse("list");
        assertEquals(ListCommand.class, cmd.getClass());
    }

    @Test
    public void parse_markWithIndex_success() throws BareumException {
        Command cmd = Parser.parse("mark 1");
        assertEquals(MarkCommand.class, cmd.getClass());
    }

    @Test
    public void parse_markMissingIndex_exceptionThrown() {
        try {
            Parser.parse("mark");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! Please give the index of the task you would like to mark.\n"
                    + "\nCorrect format: mark <index>", e.getMessage());
        }
    }

    @Test
    public void parse_unmarkWithIndex_success() throws BareumException {
        Command cmd = Parser.parse("unmark 1");
        assertEquals(UnmarkCommand.class, cmd.getClass());
    }

    @Test
    public void parse_unmarkMissingIndex_exceptionThrown() {
        try {
            Parser.parse("unmark");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! Please give the index of the task you would like to unmark.\n"
                    + "\nCorrect format: unmark <index>", e.getMessage());
        }
    }

    @Test
    public void parse_deleteWithIndex_success() throws BareumException {
        Command cmd = Parser.parse("delete 1");
        assertEquals(DeleteCommand.class, cmd.getClass());
    }

    @Test
    public void parse_deleteMissingIndex_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! Please give the index of the task you would like to delete.\n"
                    + "\nCorrect format: delete <index>", e.getMessage());
        }
    }

    @Test
    public void parse_findWithKeyword_success() throws BareumException {
        Command cmd = Parser.parse("find book");
        assertEquals(FindCommand.class, cmd.getClass());
    }

    @Test
    public void parse_findMissingKeyword_exceptionThrown() {
        try {
            Parser.parse("find");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! Please give the keyword you would like to search for.\n"
                    + "\nCorrect format: find <keyword>", e.getMessage());
        }
    }

    @Test
    public void parse_tagWithIndexTag_success() throws BareumException {
        Command cmd = Parser.parse("tag 1 /tag CS2130T");
        assertEquals(TagCommand.class, cmd.getClass());
    }

    @Test
    public void parse_tagMissingDetails_exceptionThrown() {
        try {
            Parser.parse("tag");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! The details of your tag are missing :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>", e.getMessage());
        }
    }

    @Test
    public void parse_tagMissingIndex_exceptionThrown() {
        try {
            Parser.parse("tag /tag CS2130T");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the index of the task you would like to tag :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>", e.getMessage());
        }
    }

    @Test
    public void parse_tagMissingTagTagDescription_exceptionThrown() {
        try {
            Parser.parse("tag 1");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the tag you would like to add :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>", e.getMessage());
        }
    }

    @Test
    public void parse_tagWithTagMissingTagDescription_exceptionThrown() {
        try {
            Parser.parse("tag 1 /tag");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the tag you would like to add :(\n"
                    + "\nCorrect format: tag <index> /tag <keyword>", e.getMessage());
        }
    }

    @Test
    public void parse_todo_success() throws BareumException {
        Command cmd = Parser.parse("todo borrow book");
        assertEquals(AddTodoCommand.class, cmd.getClass());
    }

    @Test
    public void parse_todoMissingDescription_exceptionThrown() {
        try {
            Parser.parse("todo");
            fail();
        } catch (BareumException e) {
            assertEquals("Oops! Please include the description of your todo :(\n" +
                    "\nCorrect format: todo <description>", e.getMessage());
        }
    }

    @Test
    public void parse_deadline_success() throws BareumException {
        Command cmd = Parser.parse("deadline return book /by 2023-09-02");
        assertEquals(AddDeadlineCommand.class, cmd.getClass());
    }

    @Test
    public void parse_deadlineMissingDetails_exceptionThrown() {
        try {
            Parser.parse("deadline");
        } catch (BareumException e) {
            assertEquals("Oops! The details of your deadline are missing :(\n" +
                    "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineMissingDescription_exceptionThrown() {
        try {
            Parser.parse("deadline /by 2023-09-02");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the description of your deadline :(\n" +
                    "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineMissingByDueDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the due date of your deadline :(\n" +
                    "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>", e.getMessage());
        }
    }

    @Test
    public void parse_deadlineWithByMissingDueDate_exceptionThrown() {
        try {
            Parser.parse("deadline return book /by");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the due date of your deadline :(\n" +
                    "\nCorrect format: deadline <description> /by <due date in YYYY-MM-DD>", e.getMessage());
        }
    }

    @Test
    public void parse_event_success() throws BareumException {
        Command cmd = Parser.parse("event project meeting /from Mon 2pm /to 4pm");
        assertEquals(AddEventCommand.class, cmd.getClass());
    }

    @Test
    public void parse_eventMissingDetails_exceptionThrown() {
        try {
            Parser.parse("event");
        } catch (BareumException e) {
            assertEquals("Oops! The details of your event are missing :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventMissingDescription_exceptionThrown() {
        try {
            Parser.parse("event /from Mon 2pm /to 4pm");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the description of your event :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventMissingFromStartTime_exceptionThrown() {
        try {
            Parser.parse("event project meeting /to 4pm");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the start time of your event :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithFromMissingStartTime_exceptionThrown() {
        try {
            Parser.parse("event project meeting /from /to 4pm");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the start time of your event :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventMissingToEndTime_exceptionThrown() {
        try {
            Parser.parse("event project meeting /from Mon 2pm");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the end time of your event :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_eventWithToMissingEndTime_exceptionThrown() {
        try {
            Parser.parse("event project meeting /from Mon 2pm /to");
        } catch (BareumException e) {
            assertEquals("Oops! Please include the end time of your event :(\n" +
                    "\nCorrect format: event <description> /from <start time> /to <end time>", e.getMessage());
        }
    }

    @Test
    public void parse_incorrectCommand_success() throws BareumException {
        Command cmd = Parser.parse("blah");
        assertEquals(IncorrectCommand.class, cmd.getClass());
    }
}
