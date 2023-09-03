package Main;

import Command.*;
import Exception.DukeException;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Testing the Parser.parse() method
public class ParserTest {

    @Test
    public void parseTest() throws DukeException {
        assertEquals(new ByeCommand().end(), Parser.parse("bye").end());
    }

    @Test
    public void byeByeShouldThrowDukeExceptionParseTest() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("bye bye");
                });
    }

    @Test
    public void byeShouldReturnByeCommandParseTest() throws DukeException {
        assertEquals(ByeCommand.class, Parser.parse("bye").getClass());
    }

    @Test
    public void shouldReturnEventCommandParseTest() throws DukeException {
        assertEquals(EventCommand.class, Parser.parse("event Amma Birthday Dinner " +
                "/from 02-08-2023 1800 /to 02-08-2023 2200").getClass());
    }

    @Test
    public void eventSpellingMistakeShouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
            Parser.parse("evennt Amma Birthday Dinner " +
                "/from 02-08-2023 1800 /to 02-08-2023 2200");
        });
    }

    @Test
    public void notIndicatingToShouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("evennt Amma Birthday Dinner " +
                            "/from 02-08-2023 1800");
                });
    }

    @Test
    public void notIndicatingFromShouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("evennt Amma Birthday Dinner " +
                            "/to 02-08-2023 1800");
                });
    }
}
