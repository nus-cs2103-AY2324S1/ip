package DukeUIClasses;

import DukeTasks.Task;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the Duke normal UI class.
 *
 * @author Tan Kerway
 * @see <a href=”https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing”>Credits</a>
 */
public class DukeUITest {

    // inputStream for the system.in
    private static final InputStream systemIn = System.in;
    // outputStream for System.out
    private static final PrintStream systemOut = System.out;
    // byte array input stream, to pass in input
    private ByteArrayInputStream testIn;
    // byte array output stream, to pass in output
    private static ByteArrayOutputStream testOut;

    /**
     * Sets up the output stream.
     *
     * @author Tan Kerway
     */
    @BeforeAll
    public static void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    /**
     * Accepts input and passes it into the input stream.
     *
     * @author Tan Kerway
     * @param data the test inputs to pass into it
     */
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    /**
     * Retrieves the output from the console to test against the
     * expected result.
     *
     * @author Tan Kerway
     * @return the output printed to the console, as a String
     */
    private String getOutput() {
        return testOut.toString();
    }

    /**
     * Clears the console of output.
     *
     * @author Tan Kerway
     */
    private void resetOutput() {
        testOut.reset();
    }

    @AfterAll
    public static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testGreeting() {
        resetOutput();
        String expected =
                "------------------------------------------------------------------------\n" +
                "Hello! I'm nyancatbot!\n" +
                "What can I do for nyan?\n" +
                "------------------------------------------------------------------------\n";
        DukeUI dukeUiTestInstance = new DukeUI();
        dukeUiTestInstance.greet(new ArrayList<>());
        assertEquals(expected, getOutput());
    }

    @Test
    public void testGoodbye() {
        resetOutput();
        String expected =
                "------------------------------------------------------------------------\n" +
                        "Bye. Hope to see you a-nyan soon!\n" +
                        "------------------------------------------------------------------------\n";
        DukeUI dukeUiTestInstance = new DukeUI();
        dukeUiTestInstance.sayGoodBye();
        assertEquals(expected, getOutput());
    }

    /**
     * Tests the output when the tasklist is empty
     *
     * @author Tan Kerway
     */
    @Test
    public void testEmptyTaskList() {
        resetOutput();
        String expected = "";
        assertEquals(expected, getOutput());
    }

    @Test
    public void testEchoTaskAdded() {
        resetOutput();
        Task testTask = new Task("dummy task");
        String expected =
                "------------------------------------------------------------------------\n" +
                "Got it. I've added this task:\n    " + "[ ] dummy task\n" +
                "Nyan you have 1 tasks in the list.\n" +
                "------------------------------------------------------------------------\n";
        DukeUI dukeUiTestInstance = new DukeUI();
        dukeUiTestInstance.echoTaskAdded(testTask, 1);
        assertEquals(expected, getOutput());
    }
}
