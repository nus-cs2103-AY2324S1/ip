package dook.services;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dook.DookException;

public class ParserTest {
    @Test
    public void parseFullInput_invalidInput_throwsException() {
        final Parser parser = new Parser();
        assertThrows(DookException.class, () -> parser.parseFullInput("mark poop"));
    }
}
