package duke;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ParserTest {

    @Test
    public void testList() {
        Parser parser = new Parser();
        parser.setUserInput("list");
        assertTrue(parser.list());
    }

    @Test
    public void testMark() {
        Parser parser = new Parser();
        parser.setUserInput("mark 1");
        assertTrue(parser.mark());
    }

    @Test
    public void testTodo() {
        Parser parser = new Parser();
        parser.setUserInput("todo run");
        assertTrue(parser.todo());
    }
}
