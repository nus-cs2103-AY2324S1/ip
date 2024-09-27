package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import command.ByeCommand;
import command.Command;
import duke.DukeException;
import parser.Parser;
import task.Task;

public class ParserTest {

    @Test
    public void testParseBye() {
        Parser parser = new Parser();
        Command testCommand = null;
        try {
            testCommand = parser.parse("bye");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(testCommand.getClass(), new ByeCommand().getClass());
    }

    @Test
    public void testParseInvalidCommand() {
        String input = "invalid command";
        assertThrows(DukeException.class, () -> Parser.parse(input));
    }

    @Test
    public void testParseInvalidDate1() {
        String date = "2023/17/10";
        assertFalse(Task.validateDate(date, "dd/MM/yyyy"));
    }

    @Test
    public void testParseInvalidDate2() {
        String date = "10/17/2023";
        assertFalse(Task.validateDate(date, "dd/MM/yyyy"));
    }

    @Test
    public void testParseValidDate1() {
        String date = "17/10/2023";
        assertTrue(Task.validateDate(date, "dd/MM/yyyy"));
    }

    @Test
    public void testParseValidDate2() {
        String date = "12/12/1212";
        assertTrue(Task.validateDate(date, "dd/MM/yyyy"));
    }

    @Test
    public void testParseInvalidDateTime1() {
        String date = "2023/17/10 1234";
        assertFalse(Task.validateDate(date, "dd/MM/yyyy HHmm"));
    }

    @Test
    public void testParseInvalidDateTime2() {
        String date = "17/10/2023 12345";
        assertFalse(Task.validateDate(date, "dd/MM/yyyy HHmm"));
    }

    @Test
    public void testParseValidDateTime1() {
        String date = "17/10/2023 1234";
        assertTrue(Task.validateDate(date, "dd/MM/yyyy HHmm"));
    }

    @Test
    public void testParseValidDateTime2() {
        String date = "12/12/1212 1212";
        assertTrue(Task.validateDate(date, "dd/MM/yyyy HHmm"));
    }

}
