package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * The DukeTest class contains JUnit test methods to test the behavior of the Duke class.
 */
public class DukeTest {

    private final InputStream sysInBackup = System.in;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the streams to capture output for testing.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Tests the behavior of the list command.
     */
    @Test
    public void testListCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("list".getBytes());
        System.setIn(in);
        new Duke("data/tasks.txt").run();

        String expectedOutput = "Here are the tasks in your list:\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    /**
     * Tests the behavior of the todo command.
     */
    @Test
    public void testTodoCommand() {
        ByteArrayInputStream in = new ByteArrayInputStream("todo hi".getBytes());
        System.setIn(in);
        new Duke("data/tasks.txt").run();

        String expectedOutput = "Got it. I've added this task:\n" +
          "[T][ ] hi\n" +
          "Now you have 1 tasks in the list.\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}
