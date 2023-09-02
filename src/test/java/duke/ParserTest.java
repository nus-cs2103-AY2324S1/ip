package duke;

import duke.command.*;
import duke.task.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    Parser parser;

    @BeforeEach
    public void setUp() {
        parser = new Parser();
        Duke.initialize();
    }

    @Test
    public void readInput_emptyCommand_exceptionThrown() {

        try {
            String command = "";
            Command result = parser.readInput(command);

        } catch (DukeException e) {
            assertEquals("Chewie don't see any command", e.getMessage());
        }

    }


    @Test
    public void readInput_unknownCommand_exceptionThrown() {

        try {
            String command = "lololololol";
            Command result = parser.readInput(command);

        } catch (DukeException e) {
            assertEquals("Chewie doesn't recgonize this command: lololololol", e.getMessage());
        }

    }

    @Test
    public void readInput_validTodo_success(){
        try {
            String command = "todo run";
            CreateToDoCommand result = (CreateToDoCommand)parser.readInput(command);
            CreateToDoCommand expected = new CreateToDoCommand("run");
            assertEquals(expected, result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_emptyTodo_exceptionThrown(){
        try {
            String command = "todo ";
            CreateToDoCommand result = (CreateToDoCommand)parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie says todo's description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void readInput_validDeadline_success(){
        try {
            String command = "deadline run /by 2020-12-26";
            CreateDeadlineCommand result = (CreateDeadlineCommand) parser.readInput(command);
            CreateDeadlineCommand expected = new CreateDeadlineCommand("run",
                    LocalDate.parse("2020-12-26", DateTimeFormatter.ISO_DATE));

            assertEquals(expected, result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_emptyDeadline_exceptionThrown(){
        try {
            String command = "deadline ";
            CreateDeadlineCommand result = (CreateDeadlineCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie says deadline's description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void readInput_invalidDateDeadline_exceptionThrown(){
        try {
            String command = "deadline run /by 1626";
            CreateDeadlineCommand result = (CreateDeadlineCommand) parser.readInput(command);
            fail();
        } catch (DateTimeParseException e) {
            return;
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_wrongDateFormatDeadline_exceptionThrown() {
        try {
            String command = "deadline run ";
            CreateDeadlineCommand result = (CreateDeadlineCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie says deadline's description is wrong.", e.getMessage());
        }
    }
    @Test
    public void readInput_validEvent_success(){
        try {
            String command = "event run /from 2020-12-26 /to 2021-10-10";
            CreateEventCommand result = (CreateEventCommand) parser.readInput(command);
            CreateEventCommand expected = new CreateEventCommand("run",
                    LocalDate.parse("2020-12-26",DateTimeFormatter.ISO_DATE),
                    LocalDate.parse("2021-10-10",DateTimeFormatter.ISO_DATE));

            assertEquals(expected,result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_invalidDateEvent_exceptionThrown(){
        try {
            String command = "event run /from 156156 /by 51651";
            CreateEventCommand result = (CreateEventCommand) parser.readInput(command);
            fail();
        } catch (DateTimeParseException e) {
            return;
        } catch (DukeException ignored) {

        }
    }
    @Test
    public void readInput_emptyEvent_exceptionThrown(){
        try {
            String command = "event ";
            CreateEventCommand result = (CreateEventCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie says event's description cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void readInput_wrongDateFormatEvent_exceptionThrown() {
        try {
            String command = "event run /by";
            CreateEventCommand result = (CreateEventCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie says event's description is wrong.", e.getMessage());
        }
    }
    @Test
    public void readInput_validList_success(){
        try {
            String command = "list";
            ListCommand result = (ListCommand) parser.readInput("list");
            ListCommand expected = new ListCommand();

            assertEquals(expected,result);
        } catch (DukeException ignored) {

        }

    }

    @Test
    public void readInput_validMark_success() {
        try {
            Duke.list().add(new ToDo("run"));
            MarkCommand result = (MarkCommand) parser.readInput("mark 1");
            MarkCommand expected = new MarkCommand(0);

            assertEquals(expected,result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_emptyMark_exceptionThrown() {
        try {
            String command = "mark ";
            MarkCommand result = (MarkCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie doesn't see the index of task list.",e.getMessage());
        }
    }

    @Test
    public void readInput_wrongMark_exceptionThrown() {
        try {
            String command = "mark 1";
            MarkCommand result = (MarkCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("The list doesn't have this index.", e.getMessage());
        }
    }

    @Test
    public void readInput_validUnmark_success() {
        try {
            Duke.list().add(new ToDo("run"));
            UnmarkCommand result = (UnmarkCommand) parser.readInput("unmark 1");
            UnmarkCommand expected = new UnmarkCommand(0);

            assertEquals(expected,result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_emptyUnmark_exceptionThrown() {
        try {
            String command = "unmark ";
            UnmarkCommand result = (UnmarkCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie doesn't see the index of task list.",e.getMessage());
        }
    }

    @Test
    public void readInput_wrongUnmark_exceptionThrown() {
        try {
            String command = "unmark 1";
            UnmarkCommand result = (UnmarkCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("The list doesn't have this index.", e.getMessage());
        }
    }

    @Test
    public void readInput_validDelete_success() {
        try {
            Duke.list().add(new ToDo("run"));
            DeleteCommand result = (DeleteCommand) parser.readInput("delete 1");
            DeleteCommand expected = new DeleteCommand(0);

            assertEquals(expected,result);
        } catch (DukeException ignored) {

        }
    }

    @Test
    public void readInput_emptyDelete_exceptionThrown() {
        try {
            String command = "delete ";
            DeleteCommand result = (DeleteCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("Chewie doesn't see the index of task list.",e.getMessage());
        }
    }

    @Test
    public void readInput_wrongDelete_exceptionThrown() {
        try {
            String command = "delete 1";
            DeleteCommand result = (DeleteCommand) parser.readInput(command);
            fail();
        } catch (DukeException e) {
            assertEquals("The list doesn't have this index.", e.getMessage());
        }
    }
}
