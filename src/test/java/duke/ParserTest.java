package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void exitTest() throws InvalidInputException {
        Command c = Parser.parse("bye");
        ExitTask tsk = new ExitTask();
        assertEquals(tsk, c);
    }
}
