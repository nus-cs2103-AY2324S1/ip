package Chatbot.alain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class UiTest {
    @Test
    public void testShowWelcome() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showWelcome();

        String expectedOutput = "____________________________________________________________\n" +
                "Hello! I'm Alain\nWhat can I do for you?\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowGoodbye() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showGoodbye();

        String expectedOutput = "____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }

    @Test
    public void testShowError() {
        Ui ui = new Ui();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        ui.showError("Something went wrong!");

        String expectedOutput = "____________________________________________________________\n" +
                " OOPS!!! Something went wrong!\n" +
                "____________________________________________________________\n";
        assertEquals(expectedOutput, outputStream.toString());
    }
}

