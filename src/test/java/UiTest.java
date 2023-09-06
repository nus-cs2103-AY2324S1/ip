package dialogix.main;

import main.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    @Test
    void testShowWelcome() {
        // Redirect System.out to capture printed output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui ui = new Ui();
        ui.getGreeting();

        // Restore System.out
        System.setOut(System.out);

        String expectedOutput = "____________________________________________________________\n" +
            "Hello! I'm Dialogix\n" +
            "What can I do for you?\n" +
            "____________________________________________________________\n";

        assertEquals(expectedOutput, outputStream.toString());
    }

    // Add more tests for other methods in Ui class
}
