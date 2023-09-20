package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
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
    public void noUserDirectedInput() {
        try {
            assertEquals(true, Parser.parse("Delete"));
        } catch (Exception e) {
            assertEquals("Index 1 out of bounds for length 1", e.getMessage());
        }
    }
}
