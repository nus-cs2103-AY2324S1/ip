package duke;

import duke.command.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.EventTest;
import duke.task.Todo;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parseCommand_listCommandWithNoArgument_success() throws DukeException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parseCommand_listCommandWithSomeArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("list 1");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid list command", e.getMessage());
        }
    }


    @Test
    public void parseCommand_todoCommandWithCorrectFormat_success() throws DukeException {
        Command command = Parser.parseCommand("todo sleeping");
        assertEquals(new TodoCommand("sleeping"), command);
    }

    @Test
    public void parseCommand_todoCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("todo");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWithCorrectFormat_success() throws DukeException {
        Command command = Parser.parseCommand("deadline Submit CS2103 IP /by 2023-09-01");
        assertEquals(new DeadlineCommand("Submit CS2103", LocalDate.parse("2023-09-01")), command);

    }

    @Test
    public void parseCommand_deadlineCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("deadline");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWithNoDeadline_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("deadline reading 100 books");
            fail();
        } catch (DukeException e) {
            assertEquals("Do you forget to add a deadline for the task?", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deadlineCommandWithWrongDateFormat_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("deadline reading 100 books /by THIS year");
            fail();
        } catch (DukeException e) {
            assertEquals("There is something wrong with your date! format: yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithCorrectFormat_success() throws DukeException {
        Command command = Parser.parseCommand("event Party /from 2023-01-01 /to 2023-01-02");
        assertEquals(new EventCommand("Party", LocalDate.parse("2023-01-01"),
                LocalDate.parse("2023-01-02")), command);

    }

    @Test
    public void parseCommand_eventCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("event");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithNoStartDate_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("event sleeping");
            fail();
        } catch (DukeException e) {
            assertEquals("Ouuuu, I think you miss some information, try again!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithWrongStartDateFormat_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("event sleeping /from 12 August 21 /to 2021-08-14");
            fail();
        } catch (DukeException e) {
            assertEquals("There is something wrong with your date! format: yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithWrongEndDateFormat_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("event sleeping /from 12-08-2021 /to 13-8-2021");
            fail();
        } catch (DukeException e) {
            assertEquals("There is something wrong with your date! format: yyyy-mm-dd", e.getMessage());
        }
    }

    @Test
    public void parseCommand_eventCommandWithNoEndDate_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("event sleeping /from 2023-05-05 ");
            fail();
        } catch (DukeException e) {
            assertEquals("Ouuuu, I think you miss some information, try again!", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommandWithOneIntegerIndex_success() throws DukeException {
        Command command = Parser.parseCommand("mark 1");
        assertEquals(new MarkCommand(0), command);

        command = Parser.parseCommand("mark 2");
        assertEquals(new MarkCommand(1), command);

        command = Parser.parseCommand("mark 99");
        assertEquals(new MarkCommand(98), command);
    }

    @Test
    public void parseCommand_markCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("mark");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a mark cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommandWithNonIntegerIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("mark a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void parseCommand_markCommandWithMoreThanOneIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("mark 3 4");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid mark command ?_? " +
                    "this command should follow by only ONE INTEGER", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkCommandWithOneIntegerIndex_success() throws DukeException {
        Command command = Parser.parseCommand("unmark 1");
        assertEquals(new UnmarkCommand(0), command);

        command = Parser.parseCommand("unmark 2");
        assertEquals(new UnmarkCommand(1), command);

        command = Parser.parseCommand("unmark 6");
        assertEquals(new UnmarkCommand(5), command);
    }

    @Test
    public void parseCommand_unmarkCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("unmark");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a unmark cannot be empty.", e.getMessage());
        }
    }


    @Test
    public void parseCommand_unmarkCommandWithNonIntegerIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("unmark a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unmarkCommandWithMoreThanOneIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("unmark 1 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid unmark command ?_? " +
                    "this command should follow by only ONE INTEGER", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommandWithOneIntegerIndex_success() throws DukeException {
        Command command = Parser.parseCommand("delete 1");
        assertEquals(new DeleteCommand(0), command);

        command = Parser.parseCommand("delete 100");
        assertEquals(new DeleteCommand(99), command);
    }

    @Test
    public void parseCommand_deleteCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("delete");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a delete cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommandWithNonIntegerIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("delete a");
            fail();
        } catch (DukeException e) {
            assertEquals("Please type in INTEGER after this command ^v^", e.getMessage());
        }
    }

    @Test
    public void parseCommand_deleteCommandWithMoreThanOneIndex_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("delete 1 2");
            fail();
        } catch (DukeException e) {
            assertEquals("Invalid delete command ?_? " +
                    "this command should follow by only ONE INTEGER", e.getMessage());
        }
    }

    @Test
    public void parseCommand_exitCommand_success() throws DukeException {
        Command command = Parser.parseCommand("exit");
        assertTrue(command instanceof ExitCommand);
    }

    @Test
    public void parseCommand_doNothingCommand_success() throws DukeException {
        Command command = Parser.parseCommand("");
        assertTrue(command instanceof DoNothingCommand);
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            Parser.parseCommand("haha");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parseCommand_findCommandWithOneKeyword_success() throws DukeException{
        Command command = Parser.parseCommand("find book");
        assertEquals(new FindCommand("book"), command);
    }

    @Test
    public void parseCommand_findCommandWithEmptyArgument_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("find");
            fail();
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a find cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parseCommand_findCommandWithMoreThanOneKeywords_exceptionThrown() throws DukeException {
        try {
            Parser.parseCommand("find abd cde");
            fail();
        } catch (DukeException e) {
            assertEquals("You only allowed to type ONE keyword!", e.getMessage());
        }
    }

    @Test
    public void parseSaveFile_notDoneTodoTask_success() {
        String taskToParsed = "0/sleeping";
        Todo expectedTask = new Todo("sleeping");
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }

    @Test
    public void parseSaveFile_doneTodoTask_success() {
        String taskToParsed = "1/sleeping";
        Todo expectedTask = new Todo("sleeping");
        expectedTask.mark();
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }

    @Test
    public void parseSaveFile_notDoneDeadlineTask_success() {
        String taskToParsed = "0/sleeping/2022-11-11";
        Deadline expectedTask = new Deadline("sleeping", LocalDate.parse("2022-11-11"));
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }

    @Test
    public void parseSaveFile_doneDeadlineTask_success() {
        String taskToParsed = "1/sleeping/2022-11-11";
        Deadline expectedTask = new Deadline("sleeping", LocalDate.parse("2022-11-11"));
        expectedTask.mark();
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }

    @Test
    public void parseSaveFile_notDoneEventTask_success() {
        String taskToParsed = "0/sleeping/2022-11-11/2022-12-12";
        Event expectedTask = new Event("sleeping", LocalDate.parse("2022-11-11"),
                LocalDate.parse("2022-12-12"));
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }

    @Test
    public void parseSaveFile_DoneEventTask_success() {
        String taskToParsed = "0/sleeping/2022-11-11/2022-12-12";
        Event expectedTask = new Event("sleeping", LocalDate.parse("2022-11-11"),
                LocalDate.parse("2022-12-12"));
        expectedTask.mark();
        assertEquals(expectedTask, Parser.parseSaveFile(taskToParsed));
    }


}
