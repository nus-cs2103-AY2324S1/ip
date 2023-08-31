package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnrecognisedCommand;
import duke.exception.DukeBadInputException;
import duke.exception.DukeLoadingException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.TodoTask;

public class ParserTest {

    @Test
    public void parseCommand_correctInputs() {
        try {
            // bye command
            assertEquals(new ExitCommand(), Parser.parse("bye"));
            // list command
            assertEquals(new ListCommand(), Parser.parse("list"));
            // help command
            assertEquals(new HelpCommand(), Parser.parse("help"));
            // unrecognised command
            assertEquals(new UnrecognisedCommand(), Parser.parse("What is this"));
            // mark command
            assertEquals(new MarkCommand(true, 1), Parser.parse("mark 1"));
            // unmark command
            assertEquals(new MarkCommand(false, 1), Parser.parse("unmark 1"));
            // delete command
            assertEquals(new DeleteCommand(1), Parser.parse("delete 1"));
            // Find command
            assertEquals(new FindCommand("testString"), Parser.parse("Find testString"));
            // deadline command
            // todo command
            assertEquals(new TodoCommand("testing1"), Parser.parse("todo testing1"));
            // deadline command
            assertEquals(new DeadlineCommand(LocalDateTime.parse("2023-05-07T03:03"), "testing"),
                    Parser.parse("deadline testing /by 2023-05-07 03:03"));
            // Event command
            assertEquals(new EventCommand(LocalDateTime.parse("2023-05-07T03:03"),
                            LocalDateTime.parse("2023-05-07T03:05"), "testingEvent"),
                    Parser.parse("event testingEvent /from 2023-05-07 03:03 /to 2023-05-07 03:05"));
        } catch (DukeBadInputException e) {
            fail();
        }
    }
    @Test
    public void parseCommand_badIndex_exceptionThrown() {
        // Bad index
        Exception e = assertThrows(NumberFormatException.class, () -> Parser.parse("mark gan"));
        assertEquals("For input string: \"gan\"", e.getMessage());
        // Bad index
        e = assertThrows(NumberFormatException.class, () -> Parser.parse("delete gan"));
        assertEquals("For input string: \"gan\"", e.getMessage());
        // no index
        e = assertThrows(DukeBadInputException.class, () -> Parser.parse("mark"));
        assertEquals("Quack requires exactly one number after the mark command", e.getMessage());
        // no index
        e = assertThrows(DukeBadInputException.class, () -> Parser.parse("delete"));
        assertEquals("Quack requires exactly one number after the delete command", e.getMessage());
    }

    @Test
    public void parseCommand_badFlag_exceptionThrown() {
        // no flag
        Exception e = assertThrows(DukeBadInputException.class, () -> Parser.parse("deadline test"));
        assertEquals("Quack cant find the required /by flags, please provide quack with one please", e.getMessage());
        // missing flag
        e = assertThrows(DukeBadInputException.class, () -> Parser.parse("event test /from 2020-12-12 12:12"));
        assertEquals("Quack cant find the required /to flags, please provide quack with one please", e.getMessage());
        //bad flag input
        e = assertThrows(DateTimeParseException.class, () ->
                Parser.parse("event test /from 2020-12-12 /to 2020-12-12 23:59"));
        assertEquals("Text '2020-12-12' could not be parsed at index 10", e.getMessage());
    }

    @Test
    public void parseCommand_noDesc_exceptionThrown() {
        // no desc todo
        Exception e = assertThrows(DukeBadInputException.class, () -> Parser.parse("todo"));
        assertEquals("Quack doesn't understand an empty todo description, please provide one!!", e.getMessage());
        // no desc event
        e = assertThrows(DukeBadInputException.class, () ->
                Parser.parse("event /from 2020-12-12 12:12 /to 2020-12-12 23:59"));
        assertEquals("Quack doesn't understand an empty description, please provide one!!", e.getMessage());
        //no desc deadline
        e = assertThrows(DukeBadInputException.class, () ->
                Parser.parse("deadline /by 2020-12-12 12:12"));
        assertEquals("Quack doesn't understand an empty description, please provide one!!", e.getMessage());
    }

    @Test
    public void parseStorage_correctInputs() {
        try {
            // tod0 command and marked
            assertEquals(new TodoTask("testing1", true), Parser.fromStorage("TODO#testing1#1"));
            // deadline command
            assertEquals(new DeadlineTask(LocalDateTime.parse("2023-10-01T23:59"), "test1"),
                    Parser.fromStorage("DEADLINE#test1#0#2023-10-01T23:59"));
            // Event command and mark
            assertEquals(new EventTask(LocalDateTime.parse("2023-05-07T03:03"),
                            LocalDateTime.parse("2023-05-07T03:05"), "testingEvent", true),
                    Parser.fromStorage("EVENT#testingEvent#1#2023-05-07T03:03#2023-05-07T03:05"));
        } catch (DukeLoadingException e) {
            System.out.println(e.getMessage());
            fail();
        }
    }
}
