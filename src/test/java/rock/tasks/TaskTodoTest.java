package rock.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTodoTest {
    static String TASK_NAME = "TEST";
    @Test
    public void checkEquality() {
        final TaskTodo TASK_DUMMY_1 = new TaskTodo(TASK_NAME);
        final TaskTodo TASK_DUMMY_2 = new TaskTodo(TASK_NAME);
        assertEquals(TASK_DUMMY_1, TASK_DUMMY_2);
    }

    @Test
    public void getDate() {
        final TaskTodo TASK_DUMMY = new TaskTodo(TASK_NAME);
        assertEquals(null, TASK_DUMMY.getDate());
    }
}
