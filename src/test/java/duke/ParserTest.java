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

    @Test
    public void deleteTest() throws InvalidInputException {
        Command c = Parser.parse("delete 3");
        DeleteTask tsk = new DeleteTask(3);
        assertEquals(tsk, c);
    }
}
