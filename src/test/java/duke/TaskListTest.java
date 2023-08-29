package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskListTest {
    
    @Test
    public void testAdd() {
        TaskList list = new TaskList();
        Todo todo = new Todo("read book");
        Deadline deadline = new Deadline("return book", "Monday");
        Event event = new Event("project meeting", "Mon 2pm", "4pm");

        list.add(todo);
        list.add(deadline);
        list.add(event);

        assertEquals(3, list.getSize());
        assertEquals(todo, list.getTask(0));
        assertEquals(deadline, list.getTask(1));
        assertEquals(event, list.getTask(2));
        assertEquals("[T][ ] read book", list.getTask(0).toString());
        assertEquals("[D][ ] return book (by: Monday)", list.getTask(1).toString());
        assertEquals("[E][ ] project meeting (from: Mon 2pm to: 4pm)", list.getTask(2).toString());
    }
}


