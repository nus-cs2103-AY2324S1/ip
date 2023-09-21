package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

/**
 * Tests Parser class.
 */
public class ParserTest {

    /**
     * Tests parse function with command bye.
     */
    @Test
    public void parse_bye() {
        Parser p = new Parser("bye");
        String farewellMessage = Ui.farewellMessage();
        assertEquals(farewellMessage, p.parse());
    }

    /**
     * Tests parse function with unknown command.
     */
    @Test
    public void parse_unknownCommand_exceptionThrown() {
        try {
            Parser p = new Parser("fvanfjpqner");
            String response = p.parse();
        } catch (DukeException e) {
            String errorMessage = "OOPS!!! I'm sorry, but I don't know what that means :-(";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /**
     * Tests createToDoFromCommand.
     */
    @Test
    public void createToDoFromCommand() {
        Parser p = new Parser("todo homework");
        ToDo t = p.createToDoFromCommand();
        String expected = new ToDo("homework").toString();
        assertEquals(expected, t.toString());
    }

    /**
     * Tests createToDoFromCommand with empty description.
     */
    @Test
    public void createToDoFromCommand_emptyDescription_exceptionThrown() {
        try {
            Parser p = new Parser("todo");
            String response = p.parse();
        } catch (DukeException e) {
            String errorMessage = "OOPS!!! The description of a todo cannot be empty.";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /**
     * Tests createDeadlineFromCommand.
     */
    @Test
    public void createDeadlineFromCommand() {
        String date = "2022-02-02";
        Parser p = new Parser("deadline read article /by " + date);
        Deadline t = p.createDeadlineFromCommand();
        String expected = new Deadline("read article", LocalDate.parse(date)).toString();
        assertEquals(expected, t.toString());
    }

    /**
     * Tests createDeadlineFromCommand without /by to specify deadline.
     */
    @Test
    public void createDeadlineFromCommand_withoutBy_exceptionThrown() {
        try {
            Parser p = new Parser("deadline read article 2022-02-02");
            String response = p.parse();
        } catch (DukeException e) {
            String errorMessage = "Please specify the deadline using /by";
            assertEquals(errorMessage, e.getMessage());
        }
    }

    /**
     * Tests createDeadlineFromCommand with empty description.
     */
    @Test
    public void createDeadlineFromCommand_emptyDescription_exceptionThrown() {
        try {
            String date = "2022-02-02";
            Parser p = new Parser("deadline /by " + date);
            String response = p.parse();
        } catch (DukeException e) {
            String errorMessage = "The description of a deadline cannot be empty.";
            assertEquals(errorMessage, e.getMessage());
        }
    }
}
