package duke;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void testParseTodo() {
        String sampleInput = "todo go for run";
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.parse(sampleInput);
        Task task = tasks.getTasks().get(0);
        assertEquals("[T][ ] go for run", task.toString());
    }

    @Test
    public void testParseEvent() {
        String sampleInput = "event dance party /from 2023-08-01 /to 2023-08-02";
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.parse(sampleInput);
        Task task = tasks.getTasks().get(0);
        assertEquals("[E][ ] dance party (from: Aug 1 2023 to: Aug 2 2023)", task.toString());
    }

    @Test
    public void testParseDeadline() {
        String sampleInput = "deadline project /by 2023-08-01";
        TaskList tasks = new TaskList();
        Parser parser = new Parser(tasks);
        parser.parse(sampleInput);
        Task task = tasks.getTasks().get(0);
        assertEquals("[D][ ] project (by: Aug 1 2023)", task.toString());
    }
}
