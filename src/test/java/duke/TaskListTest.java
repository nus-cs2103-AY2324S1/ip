package duke;

import duke.task.Task;
import duke.task.TaskType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void getSize_emptyList() {
        assertEquals(0, taskList.getSize());
    }

    @Test
    public void addTask_addOneTask() {
        taskList.addTask(new Task("Test Task", TaskType.TODO));
        assertEquals(1, taskList.getSize());
    }
    

    @Test
    public void deleteTask_taskAdded_() {
        Task task = new Task("Test Task", TaskType.TODO);
        taskList.addTask(task);
        assertEquals(1, taskList.getSize());

        Task removedTask = taskList.deleteTask(0);
        assertEquals(task, removedTask);
        assertEquals(0, taskList.getSize());
    }



}
