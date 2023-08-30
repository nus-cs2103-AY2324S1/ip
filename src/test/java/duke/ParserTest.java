package duke; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;

public class ParserTest {
    @Test
    public void markTest1() {
        Parser p = new Parser();
        Exception e = assertThrows(ParserException.class, () -> p.parse("mark awmdkwa"));
        assertEquals("Please input a valid number for your index!", e.getMessage());
    }

    @Test
    public void markTest2() {
        try {
            Parser p = new Parser();
            Command c;
            for (int i = 0; i < 20; i++) {
                c = p.parse("mark " + i);
                assertEquals(new MarkCommand(i), c);
            }
        } catch (ParserException e) {
            fail("Error Thrown");
        }
    }


    @Test
    public void unmarkTest1() {
        Parser p = new Parser();
        Exception e = assertThrows(ParserException.class, () -> p.parse("unmark awmdkwa"));
        assertEquals("Please input a valid number for your index!", e.getMessage());
    }

    @Test
    public void unmarkTest2() {
        try {
        Parser p = new Parser();
            Command c;
            for (int i = 0; i < 20; i++) {
                c = p.parse("unmark " + i);
                assertEquals(new UnmarkCommand(i), c);
        }
        } catch (ParserException e) {
            fail("Error Thrown");
        }
    }
}
