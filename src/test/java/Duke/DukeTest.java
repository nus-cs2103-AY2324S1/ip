package Duke;

import Duke.parsers.InputParser;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }
    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    @Test
    public void InitTest() {
        assertEquals(1 + 12, 13);
    }

    @Test
    public void todoTest() {

        InputParser.parse("todo gym", false);
        String actual = "Got it. I've added this task:" + "\n" + "  [T][ ] gym" + "\n" + "Now you have 1 tasks in the list." + "\n" + "----------------------------------------";
        assertEquals(actual, outContent.toString());
    }
}