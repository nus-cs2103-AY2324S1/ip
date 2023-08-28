package command;

import exception.InvalidCommandException;
import exception.InvalidInputException;
import exception.MissingArgumentException;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class MarkCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void testEmptyArgument() {

        setUpStreams();
        assertThrows(MissingArgumentException.class, () -> MarkCommand.start("mark         "));
        restoreStreams();
    }

    @Test
    public void testCommandIsSubstring() {

        setUpStreams();
        assertThrows(InvalidCommandException.class, () -> MarkCommand.start("marker"));
        restoreStreams();
    }
}
