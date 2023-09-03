package spot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import spot.command.ExitCommand;
import spot.command.ListTasksOnCommand;
import spot.exception.SpotException;

public class ParserTest {
    @Test
    public void parseDate_invalidDeleteCommand_exceptionThrown() {
        try {
            assertEquals(new ExitCommand(), Parser.parseCommand("delete "));
            fail();
        } catch (SpotException e) {
            assertEquals("Spot needs more details than that!", e.getMessage());
        }
    }
    @Test
    public void parseCommand_invalidListTasksOnCommand_exceptionThrown() {
        try {
            assertEquals(new ListTasksOnCommand(LocalDate.of(2000, 12, 31)),
                    Parser.parseCommand("list tasks on 32-12-2000"));
            fail();
        } catch (SpotException e) {
            assertEquals("This doesn't seem like a valid date to Spot!"
                    + "Please make sure your date is given in this format: dd-mm-yyyy", e.getMessage());
        }
    }

    @Test
    public void parseCommand_invalidCommand_exceptionThrown() {
        try {
            assertEquals(new ExitCommand(), Parser.parseCommand("stanloona"));
            fail();
        } catch (SpotException e) {
            assertEquals("Spot doesn't know what command that is!", e.getMessage());
        }
    }

    @Test
    public void parseDate_validDate_success() throws SpotException {
        assertEquals(LocalDate.of(2003, 12, 21),
                Parser.parseDate("21-12-2003"));
        assertEquals(LocalDate.of(2001, 11, 20),
                Parser.parseDate("20-11-2001"));
        assertEquals(LocalDate.of(2001, 6, 4),
                Parser.parseDate("04-06-2001"));
    }

    @Test
    public void parseDate_invalidDay_exceptionThrown() {
        try {
            assertEquals(LocalDate.of(2003, 3, 14),
                    Parser.parseDate("31-02-2000"));
            fail();
        } catch (Exception e) {
            assertEquals("This doesn't seem like a valid date to Spot!"
                    + "Please make sure your date is given in this format: dd-mm-yyyy", e.getMessage());
        }
    }

    @Test
    public void parseDate_invalidMonth_exceptionThrown() {
        try {
            assertEquals(LocalDate.of(2003, 2, 24),
                    Parser.parseDate("01-13-2000"));
            fail();
        } catch (Exception e) {
            assertEquals("This doesn't seem like a valid date to Spot!"
                    + "Please make sure your date is given in this format: dd-mm-yyyy", e.getMessage());
        }
    }
}
