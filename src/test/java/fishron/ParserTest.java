package fishron;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void testParseTodo() {
        String description = "todo Buy milk";
        ToDo todo = Parser.parseTodo(description);

        String expectedDescription = "[T][ ] todo Buy milk";
        assertEquals(expectedDescription, todo.toString());
    }

    @Test
    public void testParseDeadline() throws FishronException {
        String description = "sleep";
        Deadline deadline = Parser.parseDeadline(description, "12-05-2023 2300");

        String expectedDescription = "[D][ ] sleep (by: May 12 2023 11:00PM)";
        assertEquals(expectedDescription, deadline.toString());
    }
}