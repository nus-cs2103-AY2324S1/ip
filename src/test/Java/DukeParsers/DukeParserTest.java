package DukeParsers;

import DukeStorage.DukeStorageDatabase;
import DukeTaskList.DukeTaskList;
import DukeTasks.Task;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests the Duke normal UI class.
 *
 * @author Tan Kerway
 * @see <a href=”https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing”>Credits</a>
 */

public class DukeParserTest {

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
    public void testHandleUserInput() {
        DukeTaskList dukeTaskListTestInstance = new DukeTaskList();
        try {
            dukeTaskListTestInstance.setDatabaseController(new DukeStorageDatabase(dukeTaskListTestInstance));
        } catch (IOException ignored) {}
        DukeParser dukeParserTestInstance = new DukeParser(dukeTaskListTestInstance);
        String expected =
                "------------------------------------------------------------------------\n" +
                "Got it. I've added this task:\n" +
                "    [T][ ] test1\n" +
                "Nyan you have " + (dukeParserTestInstance.getTaskListSize() + 1) + " tasks in the list.\n" +
                "------------------------------------------------------------------------\n";
        provideInput("todo test1");
        dukeParserTestInstance.handleUserInput();
        assertEquals(expected, getOutput());
    }

    /**
     * Tests the string parser to check if null is returned when the input string is non-numeric.
     *
     * @author Tan Kerway
     */
    @Test
    public void testNonNumericString() {
        DukeTaskList dukeTaskListTestInstance = new DukeTaskList();
        try {
            dukeTaskListTestInstance.setDatabaseController(new DukeStorageDatabase(dukeTaskListTestInstance));
        } catch (IOException ignored) {}
        DukeParser dukeParserTestInstance = new DukeParser(dukeTaskListTestInstance);
        assertNull(dukeParserTestInstance.parseString("foo bar baz"));
    }

    /**
     * Tests the string parser to check if Integer is returned on a numeric String.
     *
     * @author Tan Kerway
     */
    @Test
    public void testNumericString() {
        DukeTaskList dukeTaskListTestInstance = new DukeTaskList();
        try {
            dukeTaskListTestInstance.setDatabaseController(new DukeStorageDatabase(dukeTaskListTestInstance));
        } catch (IOException ignored) {}
        DukeParser dukeParserTestInstance = new DukeParser(dukeTaskListTestInstance);
        assertNotNull(dukeParserTestInstance.parseString("1"));
    }
}
