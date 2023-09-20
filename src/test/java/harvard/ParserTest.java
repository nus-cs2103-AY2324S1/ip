package harvard;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import harvard.DukeException;



public class ParserTest {
    @Test
    public void testParseTodo() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        Command c = parser.parse("todo read book");
        c.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("[T][ ] read book", tasks.get(0).toString());
    }

    @Test
    public void testParseDeadline() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        Command c = parser.parse("deadline return book /by 02/02/2020 1500");
        c.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("[D][ ] return book (by: Feb 02 2020 15:00)", tasks.get(0).toString());
    }

    @Test
    public void testParseEvent() throws DukeException {
        Parser parser = new Parser();
        TaskList tasks = new TaskList();
        Command c = parser.parse("event read book /from 10/02/2023 1600 /to 12/02/2023 1500");
        c.execute(tasks, new Ui(), new Storage("data/tasks.txt"));
        assertEquals("[E][ ] read book  (from: Feb 10 2023 16:00 to: Feb 12 2023 15:00)", tasks.get(0).toString());
    }
}
