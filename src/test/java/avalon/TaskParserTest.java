package avalon;

import avalon.task.Deadline;
import avalon.task.Task;
import avalon.utility.TaskParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskParserTest {

    @Test
    public void parse_deadlineCreation_success() {
        String input = "D | 1 | return book | 2019-08-18 2000";
        Task task = TaskParser.parse(input);

        assertTrue(task instanceof Deadline);
        assertTrue(task.getIsDone());
        assertEquals("return book", task.getDescription());
    }

    @Test
    public void serialize_deadlineStringify_success() {
        Task task = new Deadline("return book", "2019-08-18 2000");
        String serialized = TaskParser.serialize(task);

        assertEquals("D | 0 | return book | 2019-08-18 2000", serialized);
    }
}
