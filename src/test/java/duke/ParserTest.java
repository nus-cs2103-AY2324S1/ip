package duke;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class ParserTest {
    @Test
    public void toDoTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseToDo("todo read",list);
        assertEquals("[T][ ] read",list.getTask(0).toString());
    }
    @Test
    public void deadlineTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseDeadline("deadline return book /by 2023-10-15",list);
        assertEquals("[D][ ] return book (by: Oct 15 2023)",list.getTask(0).toString());
    }
    @Test
    public void eventTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseEvent("event book event /from 2023-10-20 /to 2023-10-21",list);
        assertEquals("[E][ ] book event (from: Oct 20 2023 to: Oct 21 2023)",list.getTask(0).toString());
    }

    @Test
    public void markTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseEvent("event book event /from 2023-10-20 /to 2023-10-21",list);
        Parser.parseMark("mark 1", list);
        assertEquals("[E][X] book event (from: Oct 20 2023 to: Oct 21 2023)",list.getTask(0).toString());
    }

    @Test
    public void unmarkTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseEvent("event book event /from 2023-10-20 /to 2023-10-21",list);
        Parser.parseMark("mark 1", list);
        Parser.parseUnmark("unmark 1", list);
        assertEquals("[E][ ] book event (from: Oct 20 2023 to: Oct 21 2023)",list.getTask(0).toString());
    }

    @Test
    public void deleteTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseEvent("event book event /from 2023-10-20 /to 2023-10-21",list);
        Parser.parseToDo("todo read",list);
        Parser.parseDelete("delete 1", list);
        assertEquals("[T][ ] read",list.getTask(0).toString());
    }

    @Test
    public void editTest() {
        TaskList list = new TaskList(new ArrayList<>());
        Parser.parseEvent("event book event /from 2023-10-20 /to 2023-10-21",list);
        Parser.parseToDo("todo read",list);
        Parser.parseEdit("edit 2 write", list);
        assertEquals("[T][ ] write",list.getTask(1).toString());
    }
}
