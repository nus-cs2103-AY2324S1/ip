import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import chatbot.Parser;



class ParserTest {

    @Test
    void testDetermineTaskType() {
        assertEquals(Parser.TaskType.TODO, Parser.determineTaskType("todo Read a book"));
        assertEquals(Parser.TaskType.DEADLINE, Parser.determineTaskType("deadline Submit assignment /by tomorrow"));
        assertEquals(Parser.TaskType.UNKNOWN, Parser.determineTaskType("unknownCommand"));
    }
}
