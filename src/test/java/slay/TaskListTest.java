package slay;

import slay.exception.DuplicatedMarkException;
import slay.task.Task;
import slay.task.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class TaskListTest {
    @Test
    public void markTask_unmarkedTask_taskMarkedAsDone() throws DuplicatedMarkException {
        List<Task> tasks = Arrays.asList(new ToDo("test_todo"));
        ArrayList<Task> inputTasks = new ArrayList<>(tasks);
        TaskList taskList = new TaskList(inputTasks);
        taskList.markTask(tasks.get(0));
        assertEquals(tasks.get(0).getStatusIcon(), "X");
    }

    @Test
    public void markTask_markedTask_throwException() throws DuplicatedMarkException {
        List<Task> tasks = Arrays.asList(new ToDo(true, "test_todo"));
        ArrayList<Task> inputTasks = new ArrayList<>(tasks);
        TaskList taskList = new TaskList(inputTasks);
        assertThrowsExactly(DuplicatedMarkException.class, () -> {
            taskList.markTask(tasks.get(0));
        });
    }
}
