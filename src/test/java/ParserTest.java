import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
public class ParserTest {
    @Test
    public void testParseEvent() {
        String userCommand = "event Project meeting /from 2023-09-10 1800 /to 2023-09-10 2100";
        Task task = Parser.parse(userCommand);

        assertTrue(task instanceof Event);
        assertEquals("Project meeting", ((Event) task).description);
    }
}
