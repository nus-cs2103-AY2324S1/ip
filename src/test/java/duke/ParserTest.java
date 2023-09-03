package duke;

import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
    public void parseAddTaskInput_failureDeadline1() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("deadline return book /by 2019-01-150", "deadline");
            task.markAsDone();
            assertEquals("[D][X] return book (by: Jan 15 2019)", task.toString());
            fail();
        } catch (InvalidTaskException e) {
            fail();
        } catch (DateTimeParseException e) {
            assertEquals("Text '2019-01-150' could not be parsed, unparsed text found at index 10",
                    e.getMessage());
        }
    }

    @Test
    public void parseAddTaskInput_failureDeadline2() {
        try {
            Parser parse = new Parser();
            Task task = parse.parseAddTaskInput("deadline return book byebye 2019-01-150", "deadline");
            task.markAsDone();
            assertEquals("[D][X] return book (by: Jan 15 2019)", task.toString());
            fail();
        } catch (InvalidTaskException e) {
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Index 5 out of bounds for length 5", e.getMessage());
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
            fail();
        } catch (ArrayIndexOutOfBoundsException e) {
            assertEquals("Index 7 out of bounds for length 7", e.getMessage());
        }
    }
}
