package duke.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.DukeException;

public class ParserTest {
    private String emptyDeadlineDate = "Please specify deadline date using /by\n" +
    "e.g. deadline report /by 2023-12-31";
    private String emptyEventDate = "Please specify event start and end dates using /from and /to\n" + 
        "e.g. event holiday /from 2023-06-01 /to 2023-06-30";
    private String invalidDate = "Please provide date with the following format: YYYY-MM-DD";
    private String invalidEndDate = "Your end date is before start date";

    @Test
    public void convertToDMY_correctDate_success() {
        try {
            assertEquals("12 Dec 2023", Parser.convertToDMY("2023-12-12"));
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void convertToDMY_invalidDate_fail() {
        try {
            Parser.convertToDMY("lol");
            fail();
        } catch (DukeException e) {
            assertEquals(invalidDate, e.toString());
        }
    }
}
