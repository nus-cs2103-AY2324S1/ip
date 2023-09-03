package jeeves.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void isNotNumber_numberInput_returnsFalse() {
        assertFalse(Parser.isNotNumber("1234"));
        
        assertFalse(Parser.isNotNumber("0"));

        assertFalse(Parser.isNotNumber("192309213098213"));
        
    }

    @Test
    public void isNotNumber_notNumberInput_returnsTrue() {
        assertTrue(Parser.isNotNumber("Hi"));

        assertTrue(Parser.isNotNumber("12319z"));

        assertTrue(Parser.isNotNumber("1902."));

        assertTrue(Parser.isNotNumber("12301 "));
        
        assertTrue(Parser.isNotNumber("@*(@#*&"));
        
        assertTrue(Parser.isNotNumber("1@"));
    }
}
