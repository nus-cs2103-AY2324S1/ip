package helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_withNoSecondArgument_missingIndexExceptionThrown(){
        try {
            Parser.parse("mark ");
            fail(); // the test should not reach this line
        } catch (Exception e) {
            assertEquals("mark needs an index after it...", e.getMessage());
        }
    }
}