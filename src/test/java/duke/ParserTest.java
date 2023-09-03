package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void from_commandNoArg_success() {
        assertDoesNotThrow(() -> Parser.from("command"));
    }

    @Test
    public void from_commandOneArg_success() {
        assertDoesNotThrow(() -> Parser.from("command arg"));
    }

    @Test
    public void from_commandOptArgs_success() {
        assertDoesNotThrow(() -> Parser.from("command arg /opt val /opt2 value2"));
    }

    @Test
    public void getCommand_valid_success() throws Exception {
        assertEquals(Parser.from("command").getCommand(), "command");
        assertEquals(Parser.from("command arg").getCommand(), "command");
        assertEquals(Parser.from("command arg /opt val").getCommand(), "command");
    }

    @Test
    public void getArg_valid_success() throws Exception {
        assertNull(Parser.from("command").getArg());
        assertEquals(Parser.from("command arg").getArg(), "arg");
        assertEquals(Parser.from("command arg /opt val").getArg(), "arg");
    }

    @Test
    public void getOptArg_valid_success() throws Exception {
        assertNull(Parser.from("command").getOptArg("opt"));
        assertNull(Parser.from("command arg").getOptArg("opt"));
        assertEquals(Parser.from("command arg /opt val").getOptArg("opt"), "val");
    }

    @Test
    public void getOptArgAsDateTime_validDateTime_success() throws Exception {
        assertEquals(
                Parser.from("command arg /opt 2023/02/01").getOptArgAsDateTime("opt"),
                LocalDateTime.of(2023, Month.FEBRUARY, 1, 0, 0)
        );
        assertEquals(
                Parser.from("command arg /opt 2023/02/01 1230").getOptArgAsDateTime("opt"),
                LocalDateTime.of(2023, Month.FEBRUARY, 1, 12, 30)
        );
    }

    @Test
    public void getOptArgAsDateTime_invalidDateTime_throws() {
        assertThrows(
                DukeException.class,
                () -> Parser.from("command arg /opt 2023-02-01").getOptArgAsDateTime("opt")
        );

        assertThrows(
                DukeException.class,
                () -> Parser.from("command arg /opt aaaa /b bbbb").getOptArgAsDateTime("opt")
        );
    }
}
