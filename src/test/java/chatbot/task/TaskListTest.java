package chatbot.task;

import org.junit.jupiter.api.Test;

import chatbot.exceptions.InvalidDescriptionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TaskListTest {
    
    @Test
    public void NoDescriptionTest() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidDescriptionException.class, () -> tasks.addTask("", TaskType.TODO));
    }
    
    @Test
    public void WrongDescriptionTest() {
        TaskList tasks = new TaskList();
        assertThrows(InvalidDescriptionException.class, () -> tasks.addTask("test /by", TaskType.DEADLINE));
        assertThrows(InvalidDescriptionException.class, () -> tasks.addTask("test /from /to", TaskType.EVENT));
    }

    @Test
    public void AddSuccessTest() {
        TaskList tasks = new TaskList();
        tasks.addTask("chicken", TaskType.TODO);
        tasks.addTask("chicken /by 1900-01-01 1200", TaskType.DEADLINE);
        tasks.addTask("chicken /from 1900-01-01 1200 /to 1950-01-01 0000", TaskType.EVENT);

        assertEquals(tasks.getTask(1).toString(), "[T][ ] chicken");
        assertEquals(tasks.getTask(2).toString(), "[D][ ] chicken (by: Jan 01 1900 12PM)");
        assertEquals(tasks.getTask(3).toString(), "[E][ ] chicken (from: Jan 01 1900 12PM | to: Jan 01 1950 12AM)");
    }
}
