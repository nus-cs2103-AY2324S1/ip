package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import duke.*;
public class ParserTest {
    Parser parser = new Parser();
    TaskList list = new TaskList();

    @Test
    public void parserTodoTest() throws DukeException {
        assertEquals("OK DONE ALR added your todo ah:\n" +
                "[T][ ] read\n" +
                "Got 1 task in list boy", parser.parse("todo read", list));
    }

    @Test
    public void parserDeadlineTest() throws DukeException {
        assertEquals("OK DONE ALR added your Deadline ah:\n" +
                "[D][ ] return book (by: Dec 02 2019 18:00)\n" +
                "Got 1 task in list boy", parser.parse("deadline return book /by 2/12/2019 1800", list));
    }

    @Test
    public void parserEventTest() throws DukeException {
        assertEquals("OK DONE ALR added your Event ah:\n" +
                "[E][ ] project meeting (from: Mon 2pm to: 4pm)\n" +
                "Got 1 task in list boy", parser.parse("event project meeting /from Mon 2pm /to 4pm", list));
    }
}
