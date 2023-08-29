package dook.services;

import dook.DookException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {
    @Test
    public void parseFullInput_invalidInput_throwsException() {
        final Parser parser = new Parser();
        assertThrows(DookException.class, () -> parser.parseFullInput("mark poop"));
    }
}
