package duke.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.DukeException;

public class ParserTest {

    @Test
    public void parser_wrongDateFormat_exceptionThrown() {
        Parser parser = new Parser();
        Exception exception = Assertions.assertThrows(
                DukeException.class, () -> parser.parse("deadline testTask /by 8 July 2023"));
        Assertions.assertEquals("Wrong DateTime format!! Please use 'dd-MM-yyyy HHmm'.",
                exception.getMessage());
    }
}
