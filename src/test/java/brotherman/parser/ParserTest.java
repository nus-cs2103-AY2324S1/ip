package brotherman.parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import brotherman.tasks.Todo;
import brotherman.tasks.TaskList;
import brotherman.tasks.Task;
import brotherman.ui.Ui;
public class ParserTest {
    @Test
    public void testParseTodo() {
        String description = "todo Buy milk";
        Todo todo = Parser.parseTodo(description);

        String expectedDescription = "[T][ ]todo Buy milk";
        assertEquals(expectedDescription, todo.toString());
    }
}
