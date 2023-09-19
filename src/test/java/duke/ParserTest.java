package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;


public class ParserTest {
    @Test
    public void parseAddTaskInput_successTodo() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("todo read book", "todo");
            assertEquals("[T][ ] read book", task.toString());
        } catch (InvalidTaskException e) {
            fail();
        }
    }

    @Test
    public void parseAddTaskInput_successDeadline() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("deadline return book /by 2019-01-15", "deadline");
            task.markAsDone();
            assertEquals("[D][X] return book (by: Jan 15 2019)", task.toString());
        } catch (InvalidTaskException e) {
            fail();
        }
    }

    @Test
    public void parseAddTaskInput_successEvent() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("event project meeting /from 2019-02-15 /to 2019-03-30", "event");
            assertEquals("[E][ ] project meeting (from: Feb 15 2019 to: Mar 30 2019)", task.toString());
        } catch (InvalidTaskException e) {
            fail();
        }
    }

    @Test
    public void parseAddTaskInput_failureDeadline() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("deadline return book /by 2019-01-150", "deadline");
            task.markAsDone();
            assertEquals("[D][X] return book (by: Jan 15 2019)", task.toString());
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("ERROR: Deadline not in YYYY-MM-DD format", e.getMessage());
        }
    }

    @Test
    public void parseAddTaskInput_failureEvent() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("event project meeting /to 2019-02-15 /from 2019-03-30", "event");
            task.markAsDone();
            assertEquals("[E][X] project meeting (from: Feb 15 2019 to: Mar 30 2019)", task.toString());
            fail();
        } catch (InvalidTaskException e) {
            assertEquals("ERROR: Missing the /to marker", e.getMessage());
        }
    }
}
