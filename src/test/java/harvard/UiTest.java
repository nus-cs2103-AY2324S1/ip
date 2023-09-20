package harvard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    //test for system print ln
    @Test
    public void testUi() {
        Ui ui = new Ui();
        ui.displayWelcome();
        assertEquals("____________________________________________________________\n" +
                "Hello! I'm Harvard\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n", outputStreamCaptor.toString());
    }

    @Test
    public void testDisplayBye() {
        Ui ui = new Ui();
        ui.displayBye();
        assertEquals("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n", outputStreamCaptor.toString());
    }
}
