package bruno;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UiTest {

    private UI ui = new UI();
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test void testDisplayLines() {
        ui.displayLines();
        assertEquals("- - - - - - - - - - - - - - - - - - - -\n", outputStreamCaptor.toString());
    }

    @Test void testDisplayGreeting() {
        String s = "Woof Woof! I'm Bruno \uD83D\uDC3E\n" + "How can I help you?";
        assertEquals(s, ui.displayGreeting());
    }

    @Test void testDisplayBye() {
        assertEquals("Bye Bye! Hope to see you again soon! \uD83D\uDC36", ui.displayBye());
    }

    @Test void testDisplayMessage() {
        ui.displayMessage("testing");
        assertEquals("testing\n", outputStreamCaptor.toString());
    }
}
