package fluke;

import fluke.exceptions.FlukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void parseTask_garbageInput_exceptionThrown() {
        try {
            Parser.parseTask("garbage input");
            fail();
        } catch (FlukeException f) {
            assertEquals("I couldn't understand the save file...", f.getMessage());
        }
    }
}
