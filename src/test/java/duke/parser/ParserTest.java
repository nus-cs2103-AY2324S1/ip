package duke.parser;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Tag;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ui.Ui;
import duke.command.ToDoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;

import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.FindCommand;
import duke.command.MarkCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.UnmarkCommand;

public class ParserTest {
    @Test
    @Tag("Basic test")
    public void dummyTest() {
        assertEquals(2, 2);
    }

    @Test
    @Tag("Basic test")
    public void parser_initialisation_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(2, 2);
    }

    @Test
    @Tag("Todo test")
    public void initialiseTodo_nonEmptyDescription_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new ToDoCommand("read book"), parser.parseCommand("todo read book"));
    }

    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    @Test
    @Tag("ToDo test")
    public void initialiseTodo_emptyDescription_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("todo"));
    }

    @Test
    @Tag("Deadline test")
    public void initialiseDeadline_nonEmptyDescription_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new DeadlineCommand("return book", LocalDateTime.parse("01/01/2020 2359", inputFormatter)),
                parser.parseCommand("deadline return book /by 01/01/2020 2359"));
    }

    @Test
    @Tag("Deadline test")
    public void initialiseDeadline_emptyDescription_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("deadline"));
    }

    @Test
    @Tag("Deadline test")
    public void initialiseDeadline_emptyDeadline_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("deadline return book /by"));
    }

    @Test
    @Tag("Event test")
    public void initialiseEvent_nonEmptyDescription_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(
                new EventCommand("project meeting", LocalDateTime.parse("01/01/2020 2359", inputFormatter),
                        LocalDateTime.parse("01/02/2020 2359", inputFormatter)),
                parser.parseCommand("event project meeting /from 01/01/2020 2359 /to 01/02/2020 2359"));
    }

    @Test
    @Tag("Event test")
    public void initialiseEvent_emptyDescription_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("event"));
    }

    @Test
    @Tag("Event test")
    public void initialiseEvent_emptyStartTime_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("event project meeting /from"));
    }

    @Test
    @Tag("Event test")
    public void initialiseEvent_emptyEndTime_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("event project meeting /from 01/01/2020 2359 /to"));
    }

    @Test
    @Tag("List test")
    public void initialiseList_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new ListCommand(), parser.parseCommand("list"));
    }

    @Test
    @Tag("Find test")
    public void initialiseFind_nonEmptyDescription_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new FindCommand("book"), parser.parseCommand("find book"));
    }

    @Test
    @Tag("Mark test")
    public void initialiseMark_nonEmptyTaskNum_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new MarkCommand(1), parser.parseCommand("mark 1"));
    }

    @Test
    @Tag("Mark test")
    public void initialiseMark_emptyTaskNum_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("mark"));
    }

    @Test
    @Tag("Unmark test")
    public void initialiseUnmark_nonEmptyTaskNum_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new UnmarkCommand(1), parser.parseCommand("unmark 1"));
    }

    @Test
    @Tag("Unmark test")
    public void initialiseUnmark_emptyTaskNum_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("unmark"));
    }

    @Test
    @Tag("Delete test")
    public void initialiseDelete_nonEmptyTaskNum_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new DeleteCommand(1), parser.parseCommand("delete 1"));
    }

    @Test
    @Tag("Delete test")
    public void initialiseDelete_emptyTaskNum_invalidCommand() {
        Parser parser = new Parser(new Ui());
        assertEquals(new InvalidCommand(), parser.parseCommand("delete"));
    }

    @Test
    @Tag("Bye test")
    public void initialiseBye_success() {
        Parser parser = new Parser(new Ui());
        assertEquals(new ByeCommand(), parser.parseCommand("bye"));
    }
}
