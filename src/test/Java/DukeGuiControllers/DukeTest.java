import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the main Duke class.
 *
 * @author Tan Kerway
 * @see <a href=”https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing”>Credits</a>
 */
public class DukeTest {

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

    @AfterAll
    public static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    @Test
    public void testInit() {
        try {
            Duke testInstance = new Duke();
        } catch (IOException ignored) {
            fail("This should not happen");
        }
    }

    /**
     * Tests a greeting and a goodbye.
     *
     * @author Tan Kerway
     */
    @Test
    public void testGreetingAndGoodbye() {
        String expected =
                "------------------------------------------------------------------------\n" +
                "Hello! I'm nyancatbot!\n" +
                "What can I do for nyan?\n" +
                "------------------------------------------------------------------------\n" +
                "------------------------------------------------------------------------\n" +
                "Bye. Hope to see you a-nyan soon!\n" +
                "------------------------------------------------------------------------\n";
        // call the main function to get user input
        Duke.main(null);
        // test the actual output against the expected
        assertEquals(expected, getOutput());
    }
}
