package hachi;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class UiTest {

    @Test
    public void testEmptyInput() {
        String emptyInput = "   \n# hello\n   ";
        InputStream in = new ByteArrayInputStream(emptyInput.getBytes());
        Ui ui = new Ui(in, System.out);
        try {
            assertEquals(ui.getInput(), "");
            fail();
        } catch (NoSuchElementException e) {
            assertEquals("No line found", e.getMessage());
        }
    }

    @Test
    public void testValidInput() {
        String validInput = "   \nwhat's up\n   ";
        InputStream in = new ByteArrayInputStream(validInput.getBytes());
        Ui ui = new Ui(in, System.out);
        try {
            assertEquals(ui.getInput(), "what's up");
        } catch (NoSuchElementException e) {
            fail();
        }
    }

}
