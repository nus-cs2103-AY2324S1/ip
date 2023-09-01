package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void invalidInputKeywordTester() throws Exception {
        try {
            assertEquals(true, Parser.parse("Dont do this"));
        } catch (IllegalArgumentException e) {
            assertEquals("No enum constant duke.Parser.TaskKeyVal.Dont", e.getMessage());
        }
    }

    @Test
    public void noUserDirectedInput() throws Exception {
        try {
            assertEquals(true, Parser.parse("Delete"));
        } catch (DukeException e) {
            assertEquals(" ☹ OOPS!!! The description of a task cannot be empty.", e.getMessage());
        }
    }
}
