package duke;

import duke.command.AddCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteComand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    // Success test cases
    @Test
    void parse_byeCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("bye");
        Command resCommand = new ByeCommand(resString);
        assertEquals(resCommand, Parser.parse("bye"));
    }

    @Test
    void parse_listCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("list");
        Command resCommand = new ListCommand(resString);
        assertEquals(resCommand, Parser.parse("list"));
    }

    @Test
    void parse_markCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("3");
        Command resCommand = new MarkCommand(resString);
        assertEquals(resCommand, Parser.parse("mark 3"));
    }

    @Test
    void parse_unmarkCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("4");
        Command resCommand = new UnmarkCommand(resString);
        assertEquals(resCommand, Parser.parse("unmark 4"));
    }

    @Test
    void parse_deleteCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("1");
        Command resCommand = new DeleteComand(resString);
        assertEquals(resCommand, Parser.parse("delete 1"));
    }

    @Test
    public void parse_validTodoCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("coding");
        Command resCommand = new AddCommand(resString, "T");
        assertEquals(resCommand, Parser.parse("todo coding"));
    }

    @Test
    public void parse_validDeadlineCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("typing");
        resString.add("2023-05-09");
        Command resCommand = new AddCommand(resString, "D");
        assertEquals(resCommand, Parser.parse("deadline typing /by 2023-05-09"));
    }

    @Test
    public void parse_validEventCommand_success() throws DukeException {
        ArrayList<String> resString = new ArrayList<>();
        resString.add("hacking");
        resString.add("2023-05-09");
        resString.add("2023-05-10");
        Command resCommand = new AddCommand(resString, "E");
        assertEquals(resCommand, Parser.parse("event hacking /from 2023-05-09 /to 2023-05-10"));
    }

    // Exception thrown test cases

    @Test
    void parse_byeCommandSpellingError_exceptionThrown() throws DukeException {
        try {
            ArrayList<String> resString = new ArrayList<>();
            Command resCommand = new ByeCommand(resString);
            assertEquals(resCommand, Parser.parse("byebye"));
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! This command is invalid.", e.getMessage());
        }
    }

    @Test
    void parse_listCommandSpellingError_exceptionThrown() throws DukeException {
        try {
            ArrayList<String> resString = new ArrayList<>();
            Command resCommand = new ListCommand(resString);
            assertEquals(resCommand, Parser.parse("listall"));
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! This command is invalid.", e.getMessage());
        }
    }

    @Test
    void parse_markCommandNoTaskNumber_exceptionThrown() throws DukeException {
        try {
            ArrayList<String> resString = new ArrayList<>();
            Command resCommand = new MarkCommand(resString);
            assertEquals(resCommand, Parser.parse("mark"));
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The task number to mark cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parse_unmarkCommandNoTaskNumber_exceptionThrown() throws DukeException {
        try {
            ArrayList<String> resString = new ArrayList<>();
            Command resCommand = new UnmarkCommand(resString);
            assertEquals(resCommand, Parser.parse("unmark"));
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The task number to unmark cannot be empty.", e.getMessage());
        }
    }

    @Test
    void parse_deleteCommandNoTaskNumber_exceptionThrown() throws DukeException {
        try {
            ArrayList<String> resString = new ArrayList<>();
            Command resCommand = new DeleteComand(resString);
            assertEquals(resCommand, Parser.parse("delete"));
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The task number to delete cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_todoCommandSpellingMistake_exceptionThrown() {
        try {
            ArrayList<String> string = new ArrayList<>();
            Command command = new AddCommand(string, "T");
            assertEquals(command, Parser.parse("todoj"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! This command is invalid.", e.getMessage());
        }
    }

    @Test
    public void parse_deadLineCommandNoDesc_exceptionThrown() {
        try {
            ArrayList<String> string = new ArrayList<>();
            Command command = new AddCommand(string, "D");
            assertEquals(command, Parser.parse("deadline"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The format of the deadline command is invalid.\n"
                    + "Here is an example of a valid format:"
                    + " deadline coding /by 2023-09-04", e.getMessage());
        }
    }

    @Test
    public void parse_eventCommandWrongOrder_exceptionThrown() {
        try {
            ArrayList<String> string = new ArrayList<>();
            Command command = new AddCommand(string, "E");
            assertEquals(command, Parser.parse("event /to 2023-10-10 /from 2023-01-01"));
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("☹ OOPS!!! The format of the event command is invalid.\n"
                    + "Here is an example of a valid format:"
                    + " event coding /from 2023-01-01 /to 2023-12-31", e.getMessage());
        }
    }
}
