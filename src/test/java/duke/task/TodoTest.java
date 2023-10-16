package duke.task;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TodoTest {
    @Test
    @DisplayName("Test assert Todo Creation")
    public void creation() {
        ToDo todo1 = new ToDo("Read notes");
        ToDo todo2 = new ToDo("Read notes");
        assertEquals(todo1.toString(), todo2.toString());
    }
    @Test
    @DisplayName("Test assert Todo UrgencyDate")
    public void urgencyDate() {
        ToDo todo1 = new ToDo("Complete Quiz");
        ToDo todo2 = new ToDo("Complete Quiz 4");
        assertEquals(todo1.getUrgencyDate().getHour(), todo2.getUrgencyDate().getHour());
    }

    @Test
    @DisplayName("Test assert Todo Parent Task")
    public void parentTask() {
        ToDo todo1 = new ToDo("Complete Quiz");
        ToDo todo2 = new ToDo("Complete Quiz 4");
        assertEquals(todo1.getParentTask(), todo2.getParentTask());
    }
}
