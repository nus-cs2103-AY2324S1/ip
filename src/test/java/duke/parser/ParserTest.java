package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {
    private static final String INVALID_DATE = "Please provide a valid date with the following format: YYYY-MM-DD";

    @Test
    public void convertToDmy_correctDate_success() {
        try {
            assertEquals("12 Dec 2023", Parser.convertToDmy("2023-12-12"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void convertToDmy_invalidDate_fail() {
        try {
            Parser.convertToDmy("lol");
            fail();
        } catch (DukeException e) {
            assertEquals(INVALID_DATE, e.toString());
        }
    }
}
