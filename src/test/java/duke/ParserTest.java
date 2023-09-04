package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void parse_bye_success() throws DukeException{
        assertEquals("", new Parser(null).parse("88"));
    }
    @Test
    public void parse_undefinedInput_exceptionThrown() {
        try{
            assertEquals("⚠ Sorry! I am not able to understand you. Try another language:D", new Parser(null).parse("undefined"));
            fail();
        } catch (DukeException e) {
            assertEquals("undefined", e.getMessage());
        }
    }

}
