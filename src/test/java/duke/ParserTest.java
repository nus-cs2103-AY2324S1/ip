package duke;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void toDoTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("todo read",list);
        assertEquals("[T][ ] read",list.getTask(0).toString());
    }
    @Test
    public void deadlineTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("deadline return book /by 2023-10-15",list);
        assertEquals("[D][ ] return book (by: Oct 15 2023)",list.getTask(0).toString());
    }
    @Test
    public void eventTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("event book event /from 1pm /to 2pm",list);
        assertEquals("[E][ ] book event (from: 1pm to: 2pm)",list.getTask(0).toString());
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("event book event /from 1pm /to 2pm",list);
        Parser.parse("mark 1", list);
        assertEquals("[E][X] book event (from: 1pm to: 2pm)",list.getTask(0).toString());
    }

    @Test
    public void unmarkTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("event book event /from 1pm /to 2pm",list);
        Parser.parse("mark 1", list);
        Parser.parse("unmark 1", list);
        assertEquals("[E][ ] book event (from: 1pm to: 2pm)",list.getTask(0).toString());
    }

    @Test
    public void deleteTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parse("event book event /from 1pm /to 2pm",list);
        Parser.parse("todo read",list);
        Parser.parse("delete 1", list);
        assertEquals("[T][ ] read",list.getTask(0).toString());
    }
}
