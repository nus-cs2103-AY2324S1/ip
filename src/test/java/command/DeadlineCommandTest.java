package command;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineCommandTest {
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
    public void dmySlashFormat(){
        setUpStreams();
        DeadlineCommand.start();

        String deadline = "deadline";
        ByteArrayInputStream input = new ByteArrayInputStream(deadline.getBytes());
        System.setIn(input);

        assertEquals("hello", outContent.toString());

        String bye = "bye";
        input = new ByteArrayInputStream(bye.getBytes());
        System.setIn(input);
        restoreStreams();
    }
}
