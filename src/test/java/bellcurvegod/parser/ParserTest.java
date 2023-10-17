package bellcurvegod.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parse_tooManySpaces_exceptionThrown() {
        try {
            Parser.parse("todo  eat");
            fail();
        } catch (Exception e) {
            assertEquals("You've entered too many spaces between each words. "
                + "Please ensure there is only one space between each word.\n", e.getMessage());
        }
    }

    @Test
    public void parse_nonExistentTask_exceptionThrown() {
        try {
            Parser.parse("delete 1000000");
            fail();
        } catch (Exception e) {
            assertEquals("There is no task with index 1000000 in your task list. "
                + "Please check your input.\n", e.getMessage());
        }
    }

    @Test
    public void parse_nonIntegerIndex_exceptionThrown() {
        try {
            Parser.parse("delete hello");
            fail();
        } catch (Exception e) {
            assertEquals("You have entered non-integer as indices. "
                + "Please check your input.\n", e.getMessage());
        }
    }
}
