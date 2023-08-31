package duke;

import duke.exceptions.CommandDetailException;
import duke.exceptions.CommandNotRecognizedException;
import duke.exceptions.TimeParsingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    private final Parser parser = new Parser();

    @Test
    void testInvalidCommand1() {
        assertThrows(CommandNotRecognizedException.class, () -> {
            parser.parse("invalid command");
        });
    }

    @Test
    void testInvalidCommand2() {
        assertThrows(CommandNotRecognizedException.class, () -> {
            parser.parse("invalid command 2");
        });
    }

    @Test
    void testInvalidCommand3() {
        assertThrows(CommandNotRecognizedException.class, () -> {
            parser.parse("random");
        });
    }

    @Test
    void testNoCommandDetail1() {
        assertThrows(CommandDetailException.class, () -> {
            parser.parse("todo");
        });
    }

    @Test
    void testNoCommandDetail2() {
        assertThrows(CommandDetailException.class, () -> {
            parser.parse("deadline hoiwf");
        });
    }

    @Test
    void testNoCommandDetail3() {
        assertThrows(CommandDetailException.class, () -> {
            parser.parse("event hoiwf /from 2020-08-25");
        });
    }

    @Test
    void testNoCommandDetail4() {
        assertThrows(CommandDetailException.class, () -> {
            parser.parse("event hoiwf /to 2020-08-25");
        });
    }

    @Test
    void testNoCommandDetail5() {
        assertThrows(TimeParsingException.class, () -> {
            parser.parse("event hoiwf /from 2020.08.25 /to 2020.08.25");
        });
    }

}
