package duke.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidCommandException;

public class ParserTest {
    @Test
    public void parse_emptyInput_returnsNull() {
        String[] testCases = { "", "  ", "\n  \n" };
        for (String input: testCases) {
            try {
                Command c = Parser.parse(input, false);
                System.out.println("HI " + c);
                assertNull(c);
            } catch (DukeException ex) {
                // ignore
                fail();
            }
        }
    }

    @Test
    public void parse_addCommandWithArgs_noExceptionThrown() {
        String[] testCases = {
            "todo run",
            "deadline project /by 18/08/2001 18:00",
            "event project meeting /from 18/08/2001 18:00 /to 31/08/2001 22:00"
        };

        for (String str: testCases) {
            assertDoesNotThrow(() -> Parser.parse(str, false));
        }
    }

    @Test
    public void parse_listCommand_success() {
        assertDoesNotThrow(() -> Parser.parse("list", false));
    }

    @Test
    public void parse_listCommandWithArgs_throwException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("list 3", false));
    }

    @Test
    public void parseText_validString_success() {
        String[] testCases = {"todo deadline", "event project meeting", "  hello, i am a friend  "};
        String[][] answers = {
            new String[] {"todo", "deadline"},
            new String[] {"event", "project meeting"},
            new String[] {"hello,", "i am a friend"},
        };
        for (int i = 0; i < testCases.length; i++) {
            String[] result = Parser.parseText(testCases[i]);
            assertEquals(result[0], answers[i][0]);
            assertEquals(result[1], answers[i][1]);
        }
    }

    @Test
    public void parseArgs_integerString_success() {
        assertDoesNotThrow(() -> Parser.parseArgs("3"));
    }

    @Test
    public void parseArgs_decimalString_throwException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseArgs("3.01"));
    }

    @Test
    public void parseArgs_emptyString_throwException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseArgs(" "));
        assertThrows(InvalidCommandException.class, () -> Parser.parseArgs(""));
    }

    @Test
    public void parseDateTime_dateWithCorrectFormat_success() {
        assertDoesNotThrow(() -> Parser.parseDateTime("deadline project /by 18/08/2001 18:00"));
    }

    @Test
    public void parseDateTime_dateWithWrongDay_throwException() {
        assertNull(Parser.parseDateTime("32/12/2001 18:00"));
    }

    @Test
    public void parseDateTime_dateWithWrongMonth_throwException() {
        assertNull(Parser.parseDateTime("18/13/2001 18:00"));
    }

    @Test
    public void parseDateTime_dateWithWrongYear_throwException() {
        assertNull(Parser.parseDateTime("18/12/0 18:00"));
    }

    @Test
    public void parseDateTime_wrongTime_throwException() {
        assertNull(Parser.parseDateTime("18/12/2001 25:00"));
    }

    @Test
    public void parseDateTime_wrongTimeFormat_throwException() {
        assertNull(Parser.parseDateTime("18/12/2001 1800"));
    }
}
