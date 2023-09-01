package duck.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.IllegalFormatException;
import org.junit.jupiter.api.Test;
import duck.IoHandler;
import duck.Parser;
import duck.Storage;
import duck.exceptions.DuckException;
import duck.exceptions.SyntaxException;

public class ParserTest {

    Storage storage = new Storage();
    IoHandler ioHandler = new IoHandler();
    TaskList list = new TaskList();


    @Test
    public void IdentifyTestWithCommand() {
        Parser parser = new Parser();
        String command = parser.identify("deadline return book /by 2023-03-03 1220");
        assertEquals("deadline", command);
        command = parser.identify("event attend marraige /from 2023-09-09 1220 /to 2023-09-10 1220");
        assertEquals("event", command);
        command = parser.identify("todo project");
        assertEquals("todo", command);
    }



}
