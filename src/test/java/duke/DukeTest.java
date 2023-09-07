package duke;

import duke.parsers.InputParser;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {

    private InputParser parser = new InputParser(new ArrayList<>());


    @Test
    public void InitTest() {
        assertEquals(1 + 12, 13);
    }

    @Test
    public void todoTest() {

        String actual = parser.parse("todo gym", false);
        String expected = "Got it. I've added this task:" + "\n" + "  [T][ ] gym" + "\n" + "Now you have 1 tasks in the list.\n";
        assertEquals(expected, actual);
    }
}