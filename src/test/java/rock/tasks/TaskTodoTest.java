package rock.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TaskTodoTest {
    static final String TASK_NAME = "TEST";
    @Test
    public void checkEquality() {
        final TaskTodo dummyTask1 = new TaskTodo(TASK_NAME);
        final TaskTodo dummyTask2 = new TaskTodo(TASK_NAME);
        assertEquals(dummyTask1, dummyTask2);
    }

    @Test
    public void getDate() {
        final TaskTodo dummyTask = new TaskTodo(TASK_NAME);
        assertEquals(null, dummyTask.getDate());
    }
}
