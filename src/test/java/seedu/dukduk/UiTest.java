package seedu.duke;

import org.junit.jupiter.api.Test;
import dukduk.Ui;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testPrintGreetings() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui.printGreetings();
        String expected = "____________________________________________________________" +
                System.lineSeparator() +
                " Hello! I'm Dukduk" +
                System.lineSeparator() +
                " What can I do for you?" +
                System.lineSeparator() +
                "____________________________________________________________";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(System.out);
    }

    @Test
    public void testPrintExit() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Ui.printExit();
        String expected = "Bye. Hope to see you again soon!" +
                System.lineSeparator() +
                "____________________________________________________________";

        assertEquals(expected, outputStream.toString().trim());
        System.setOut(System.out);
    }
}
