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
        ui.displayGreeting();
        String s = "                ;~~,__,\n" + ":-….,———-‘`----/   ._.*\n" + " `-,,,   BRUNO   ,’\n"
                + "     ;   ,~.——;  /\n" + "     :  |     :  |\n" + "     `_ ’     `_ ‘\n"
                + "Woof Woof! I'm Bruno \uD83D\uDC3E\n" + "How can I help you?\n[Please not that date/time "
                + "must be in \"yyyy-MM-dd HH:mm\" format]\n";
        assertEquals(s, outputStreamCaptor.toString());
    }

    @Test void testDisplayBye() {
        ui.displayBye();
        assertEquals("\tBye Bye! Hope to see you again soon! \uD83D\uDC36\n", outputStreamCaptor.toString());
    }

    @Test void testDisplayMessage() {
        ui.displayMessage("testing");
        assertEquals("testing\n", outputStreamCaptor.toString());
    }
}
