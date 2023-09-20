package duke.list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import duke.exception.KoraException;
import duke.task.ToDo;

public class TaskListTest {
    @Test
    public void initialTaskList_shouldBeEmpty() {
        TaskList taskList = new TaskList();
        Assertions.assertEquals(0, taskList.getLength());
    }

    @Test
    public void addTask_shouldAddToTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test"));
        Assertions.assertEquals(1, taskList.getLength());
    }

    @Test
    public void getTaskMoreThanTaskListLength_shouldThrowKoraException() {
        Exception exception =
                Assertions.assertThrows(KoraException.class, () -> {
                    TaskList taskList = new TaskList();
                    taskList.addTask(new ToDo("test"));
                    taskList.getTask(2);
                });
        String expected = "AiGu! There are only 1 tasks!";
        String actual = exception.getMessage();
        Assertions.assertTrue(actual.contains(expected));
    }

    @Test
    public void removeTask_shouldRemoveFromTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test"));
        taskList.removeTask(1);
        Assertions.assertEquals(0, taskList.getLength());
    }

    @Test
    public void clearTask_shouldRemoveEverythingFromTaskList() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("test"));
        taskList.clearTasks();
        Assertions.assertEquals(0, taskList.getLength());
    }
}
