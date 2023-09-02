package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void parserTest1() {
        assertEquals(Parser.parseTask("event do not steal"), Parser.TaskType.EVENT);
    }

    @Test
    public void parserTest2() {
        assertEquals(Parser.parseToDo("todo why"), "why");
    }

}
