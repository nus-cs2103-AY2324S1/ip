package duke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.storage.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class TodoTest {
    @Test
    public void addTodoTest() {
        TaskList taskList = new TaskList();
        Task testToDo = new ToDo("test1");
        taskList.addTest(testToDo);
        String status = "[To-Do][No] test1";
        assertEquals(1, taskList.size());
        assertEquals(status, taskList.get(0).getStatus());
    }
}

