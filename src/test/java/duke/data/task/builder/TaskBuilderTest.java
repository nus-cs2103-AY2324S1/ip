package duke.data.task.builder;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import org.junit.jupiter.api.Test;

public class TaskBuilderTest {
    @Test
    public void buildFromStringTest() {
        TaskBuilder taskBuilder = new TaskBuilder();
        try {
            Task t = taskBuilder.buildFromString("todo return book");
            assert t instanceof Todo;
            t = taskBuilder.buildFromString("deadline return book /by 2020-02-02");
            assert t instanceof Deadline;
            t = taskBuilder.buildFromString("event return book /from 2020-02-02 /to 2020-02-03");
            assert t instanceof Event;
            t = taskBuilder.buildFromString("task throw exception");
        } catch (Exception e) {
            assert e.getMessage().equals("Invalid Input: expected format: todo <description> "
                    + "or deadline <description> /by <by> or event <description> /from <start> /to <end>");
        }
    }
}
