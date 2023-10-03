package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class TodoTest {

    @Test
    public void createTodoTest() {
        Todo todo = new Todo("Test description");
        assertEquals(TaskType.TODO, todo.getTaskType());
        assertEquals("Test description", todo.getDescription());
    }
}
