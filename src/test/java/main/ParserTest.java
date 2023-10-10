package main;

import command.ByeCommand;

import command.EventCommand;

import exception.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


//Underscores may be used in test method names using the
// following three part format featureUnderTest_testScenario_expectedBehavior()

// Testing the Parser.parse() method
public class ParserTest {

    @Test
    public void parse_correctByeInput() throws DukeException {
        assertEquals(new ByeCommand().isContinue(), Parser.parse("bye").isContinue());
    }

    @Test
    public void parse_incorrectByeInput() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("bye bye");
                });
    }

    @Test
    public void parse_inputStartingWithEvent_shouldReturnEventCommandParseTest() throws DukeException {
        assertEquals(EventCommand.class, Parser.parse("event Amma Birthday Dinner " +
                "/from 02-08-2023 1800 /to 02-08-2023 2200").getClass());
    }

    @Test
    public void parse_eventSpellingMistake_shouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("evennt Amma Birthday Dinner " +
                            "/from 02-08-2023 1800 /to 02-08-2023 2200");
                });
    }

    @Test
    public void parse_notIndicatingTo_shouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("evennt Amma Birthday Dinner " +
                            "/from 02-08-2023 1800");
                });
    }

    @Test
    public void parse_notIndicatingFrom_shouldReturnDukeException() {
        assertThrows(DukeException.class,
                () -> {
                    Parser.parse("evennt Amma Birthday Dinner " +
                            "/to 02-08-2023 1800");
                });
    }
}
