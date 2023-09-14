package ducky.task;

import com.ducky.task.TodoTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTaskTest {

    TodoTask todoTask1 = new TodoTask("test1");
    TodoTask todoTask2 = new TodoTask("test2");

    @BeforeEach
    public void setTodoTask2ToCompleted() {
        todoTask2.setComplete();
    }
    @Test
    public void todoTask_toString() {
        assertEquals(
                todoTask1.toString(),
                "[T][ ] test1"
        );
        assertEquals(
                todoTask2.toString(),
                "[T][X] test2"
        );
    }

    @Test
    public void todoTask_getSaveFormat() {
        assertEquals(
                todoTask1.getSaveFormat(),
                "T | 0 | test1"
        );
        assertEquals(
                todoTask2.getSaveFormat(),
                "T | 1 | test2"
        );
    }
}
