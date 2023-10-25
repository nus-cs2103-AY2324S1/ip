package deterministicparrot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeterministicParrotIntegrationTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testDeterministicParrotFlow() {
        //TODO: make this test run in a temporary directory
        String simulatedUserInput = "todo test\n" +
                "deadline test2 /by 2023-01-02\n" +
                "event test3 /from 2020-01-01 /to 2020-01-02\n" +
                "list\n" +
                "bye\n";
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        DeterministicParrot.main(new String[]{});

        String expectedOutput = "    ____________________________________________________________\n" +
                "     Hello! I'm deterministicparrot.DeterministicParrot\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [T][ ] test\n" +
                "     Now you have 1 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [D][ ] test2 (by: Jan 2 2023)\n" +
                "     Now you have 2 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       [E][ ] test3 (from: Jan 1 2020 to: Jan 2 2020)\n" +
                "     Now you have 3 tasks in the list.\n" +
                "    ____________________________________________________________\n" +
                "    ____________________________________________________________\n" +
                "     Here are the tasks in your list:\n" +
                "     1. [T][ ] test\n" +
                "     2. [D][ ] test2 (by: Jan 2 2023)\n" +
                "     3. [E][ ] test3 (from: Jan 1 2020 to: Jan 2 2020)\n" +
                "\n"+
                "    ____________________________________________________________\n" +
                "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n"; // the entire expected output as shown in your example
        assertEquals(expectedOutput, outContent.toString());
    }
}
