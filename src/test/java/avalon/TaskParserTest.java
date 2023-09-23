package avalon;

import avalon.task.Deadline;
import avalon.task.Event;
import avalon.task.Task;
import avalon.task.ToDo;
import avalon.utility.TaskParser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class TaskParserTest {

    @Test
    public void testParseToDoTask() {
        String input = "T | 0 | 1 | Buy groceries";
        Task task = TaskParser.parse(input);
        assertEquals("Buy groceries", task.getDescription());
        assertEquals(false, task.getIsDone());
        assertEquals(1, task.getPriority());
        assertEquals(ToDo.class, task.getClass());
    }

    @Test
    public void testParseDeadlineTask() {
        String input = "D | 1 | 2 | Submit report | 2023-09-01 1800";
        Task task = TaskParser.parse(input);
        assertEquals("Submit report", task.getDescription());
        assertEquals(true, task.getIsDone());
        assertEquals(2, task.getPriority());
        assertEquals(Deadline.class, task.getClass());
    }

    @Test
    public void testParseEventTask() {
        String input = "E | 0 | 3 | Team meeting | 2023-09-02 1400 | 2023-09-02 1500";
        Task task = TaskParser.parse(input);
        assertEquals("Team meeting", task.getDescription());
        assertEquals(false, task.getIsDone());
        assertEquals(3, task.getPriority());
        assertEquals(Event.class, task.getClass());
    }

    @Test
    public void testSerializeToDoTask() {
        ToDo todo = new ToDo("Read a book");
        todo.setPriority(2);
        todo.markDone();
        String expected = "T | 1 | 2 | Read a book";
        String result = TaskParser.serialize(todo);
        assertEquals(expected, result);
    }

    @Test
    public void testSerializeDeadlineTask() {
        Deadline deadline = new Deadline("Assignment", "2023-09-10 2359");
        deadline.setPriority(1);
        deadline.markDone();
        String expected = "D | 1 | 1 | Assignment | 2023-09-10 2359 | 1";
        String result = TaskParser.serialize(deadline);
        assertEquals(expected, result);
    }

    @Test
    public void testSerializeEventTask() {
        Event event = new Event("Conference", "2023-09-15 0900", "2023-09-15 1700");
        event.setPriority(3);
        event.markDone();
        String expected = "E | 1 | 3 | Conference | 2023-09-15 0900 | 2023-09-15 1700 | 3";
        String result = TaskParser.serialize(event);
        assertEquals(expected, result);
    }
}
