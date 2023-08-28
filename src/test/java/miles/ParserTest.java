package miles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testGetTaskNumberMark() {
        int taskNum = Parser.getTaskNumber("mark", "mark 1");
        assertEquals(1, taskNum);
    }

    @Test 
    public void testGetTaskNumberUnmark() {
        int taskNum = Parser.getTaskNumber("unmark", "unmark 100");
        assertEquals(100, taskNum);
    }
}
