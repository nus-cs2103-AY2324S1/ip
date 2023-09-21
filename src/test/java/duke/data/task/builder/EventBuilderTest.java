package duke.data.task.builder;

import duke.data.task.Task;
import duke.exception.DukeException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventBuilderTest {
    @Test
    public void buildFromStringTest() throws DukeException {
        EventBuilder deadlineBuilder = new EventBuilder();
        Task t = deadlineBuilder.buildFromString("event return book /from 2020-02-02 /to 2020-02-03");
        assertEquals("[E][ ] return book.  (tags: event) (from: 2020-02-02 to: 2020-02-03)", t.toString());
        try {
            deadlineBuilder.buildFromString("deadline return book /by 2020-02-02 12:00");
        } catch (DukeException e) {
            assertEquals("Invalid Input: expected format: event <description> /from <start> /to <end>", e.getMessage());
        }
    }
}
