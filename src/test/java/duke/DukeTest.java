package duke;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DukeTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayInputStream testInput;
    private ByteArrayOutputStream testOutput;

    @BeforeEach
    public void setUpStreams() {
        // Redirect System.in and System.out to capture input and output
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));

        // Prepare test input
        String userInput = "todo badminton\n"; // Replace with your test input
        testInput = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(testInput);
    }

    @AfterEach
    public void restoreStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testDuke() {
        Duke duke = new Duke();
        duke.run();
        String actualOutput = testOutput.toString().trim();

        String expectedOutput = "Hello! I'm Lakinta\n" +
                "What can I do for you?\n" +
                "Got it. I've added this task:\n" +
                "[T][ ] badminton\n" +
                "Now you have 1 tasks in the list.";

        String normalizedExpected = normalizeString(expectedOutput);
        String normalizedActual = normalizeString(actualOutput);

        assertEquals(normalizedExpected, normalizedActual);
    }

    private String normalizeString(String input) {
        return input.replaceAll("\\s+", " ").replaceAll("\r\n", "\n").trim();
    }
}

