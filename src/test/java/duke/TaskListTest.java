package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;



class TaskListTest {

    @Test
    void addTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("sleep");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void getTaskListTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);

        List<Task> tasks = new ArrayList<>();
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        assertEquals(tasks, taskList.getTaskList());

    }

    @Test
    void getTaskCountTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        assertEquals(3, taskList.getTaskCount());
    }

    @Test
    void getTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        assertEquals(task1, taskList.getTask(1));
        assertEquals(task2, taskList.getTask(2));
        assertEquals(task3, taskList.getTask(3));
    }

    @Test
    void removeTask() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.removeTask(1);
        assertEquals(2, taskList.getTaskCount());
        assertEquals(task2, taskList.getTask(1));
        assertEquals(task3, taskList.getTask(2));

    }
}
