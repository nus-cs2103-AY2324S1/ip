package helpbuddy.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import helpbuddy.command.AddCommand;
import helpbuddy.command.Command;
import helpbuddy.command.DeleteCommand;
import helpbuddy.command.MarkCommand;
import helpbuddy.command.UnmarkCommand;
import helpbuddy.exception.HelpBuddyException;
import helpbuddy.task.Deadline;
import helpbuddy.task.Event;
import helpbuddy.task.ToDo;

public class ParserTest {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
    @Test
    public void parseCommand_unknownCommand_exceptionThrown1() {
        try {
            Parser.parseCommand("ashhsdlf");
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! I'm sorry, but I don't know what that means.\n", e.getMessage());
        }
    }

    @Test
    public void parseCommand_unknownCommand_exceptionThrown2() {
        try {
            Parser.parseCommand("help");
            fail();
        } catch (HelpBuddyException e) {
            assertEquals("OOPS! I'm sorry, but I don't know what that means.\n", e.getMessage());
        }
    }

    @Test
    public void parseCommand_addCommand_success1() {
        try {
            Event e = new Event(
                    "meeting",
                    LocalDateTime.parse("31/08/23 18:20", formatter),
                    LocalDateTime.parse("31/08/23 19:20", formatter)
            );
            Command expectedCmd = new AddCommand(e);
            Command actualCmd = Parser.parseCommand("event meeting /from 31/08/23 18:20 /to 31/08/23 19:20");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_addCommand_success2() {
        try {
            Deadline d = new Deadline("meeting", LocalDateTime.parse("31/08/23 18:20", formatter));
            Command expectedCmd = new AddCommand(d);
            Command actualCmd = Parser.parseCommand("deadline meeting /by 31/08/23 18:20");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_addCommand_success3() {
        try {
            ToDo d = new ToDo("meeting");
            Command expectedCmd = new AddCommand(d);
            Command actualCmd = Parser.parseCommand("todo meeting");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_deleteCommand_success() {
        try {
            Command expectedCmd = new DeleteCommand(1);
            Command actualCmd = Parser.parseCommand("delete 1");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_markCommand_success() {
        try {
            Command expectedCmd = new MarkCommand(1);
            Command actualCmd = Parser.parseCommand("mark 1");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }

    @Test
    public void parseCommand_unmarkCommand_success() {
        try {
            Command expectedCmd = new UnmarkCommand(1);
            Command actualCmd = Parser.parseCommand("unmark 1");
            assertEquals(true, expectedCmd.equals(actualCmd));
        } catch (HelpBuddyException e) {
            fail();
        }
    }
}
