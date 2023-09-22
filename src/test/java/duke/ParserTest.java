package duke;
import duke.task.Task;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void testParseTodo() {
        try {
            String sampleInput = "todo go for run";
            TaskList tasks = new TaskList();
            Parser parser = new Parser(tasks);
            parser.parse(sampleInput);
            Task task = tasks.getTasks().get(0);
            assertEquals("[T][ ] go for run", task.toString());
        } catch (DukeException e) {
            System.out.println("Exception should not be thrown");
        }
    }

    @Test
    public void testParseEvent() {
        try {
            String sampleInput = "event dance party /from 2023-08-01 /to 2023-08-02";
            TaskList tasks = new TaskList();
            Parser parser = new Parser(tasks);
            parser.parse(sampleInput);
            Task task = tasks.getTasks().get(0);
            assertEquals("[E][ ] dance party (from: Aug 1 2023 to: Aug 2 2023)", task.toString());
        } catch (DukeException e) {
            System.out.println("Exception should not be thrown");
        }
    }

    @Test
    public void testParseDeadline() {
        try {
            String sampleInput = "deadline project /by 2023-08-01";
            TaskList tasks = new TaskList();
            Parser parser = new Parser(tasks);
            parser.parse(sampleInput);
            Task task = tasks.getTasks().get(0);
            assertEquals("[D][ ] project (by: Aug 1 2023)", task.toString());
        } catch (DukeException e) {
            System.out.println("Exception should not be thrown");
        }
    }
}
