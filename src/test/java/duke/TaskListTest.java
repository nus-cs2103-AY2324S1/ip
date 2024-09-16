package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void addTodoTaskTest() {
        TaskList taskList = new TaskList();
        Task task = new Todo("sleep");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addDeadlineTaskTestWithDateTest() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("eat", "18.09.2023");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addDeadlineTaskTestWithDayTest() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("eat", "18.09.2023,Mon");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addDeadlineTaskTestWithTimeTest() {
        TaskList taskList = new TaskList();
        Task task = new Deadline("eat", "18.09.2023,Mon,4PM");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addEventTaskTestWithDateTest() {
        TaskList taskList = new TaskList();
        Task task = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addEventTaskTestWithDayTest() {
        TaskList taskList = new TaskList();
        Task task = new Event("drink", "18.09.2023,Mon", "18.09.2023,Tue");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void addEventTaskTestWithTimeTest() {
        TaskList taskList = new TaskList();
        Task task = new Event("drink", "18.09.2023,Tue,530AM", "18.09.2023,Wed5.40AM");
        taskList.addTask(task);
        assertEquals(1, taskList.getTaskCount());
    }

    @Test
    void getTaskListTestWithDate() {
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
    void getTaskListWithDayTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023,Mon");
        Task task3 = new Event("drink", "18.09.2023,Tue", "18.09.2023,Wed");
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
    void getTaskListWithTimeTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023,Mon,4PM");
        Task task3 = new Event("drink", "18.09.2023,Tue,530AM", "18.09.2023,Wed5.40AM");
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
    void getTaskCountTest1() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        assertEquals(1, taskList.getTaskCount());
        taskList.addTask(task2);
        taskList.addTask(task3);
        assertEquals(3, taskList.getTaskCount());
    }

    @Test
    void getTaskCountTest2() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        assertEquals(1, taskList.getTaskCount());
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.removeTask(2);
        assertEquals(2, taskList.getTaskCount());
    }

    @Test
    void getTaskTest() {
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
    void removeValidIndexTaskTest() {
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

    @Test
    void removeInvalidIndexTaskTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.removeTask(4);
        assertEquals(3, taskList.getTaskCount());
    }

    @Test
    void removeNegativeIndexTaskTest() {
        TaskList taskList = new TaskList();
        Task task1 = new Todo("sleep");
        Task task2 = new Deadline("eat", "18.09.2023");
        Task task3 = new Event("drink", "18.09.2023", "18.09.2023");
        taskList.addTask(task1);
        taskList.addTask(task2);
        taskList.addTask(task3);
        taskList.removeTask(-1);
        assertEquals(3, taskList.getTaskCount());
    }

    @Test
    public void testRemoveTaskEmptyList() {
        TaskList taskList = new TaskList();
        taskList.removeTask(1);
        assertEquals(0, taskList.getTaskCount());
    }

    @Test
    void findMatchingTasksWithMatchingKeywordTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("sleep"));
        taskList.addTask(new Todo("Read"));
        taskList.addTask(new Deadline("Submit report", "2023.08.31"));
        List<Task> matchingTasks = taskList.findMatchingTasks("Read");
        assertEquals(1, matchingTasks.size());
        assertTrue(matchingTasks.get(0).getDescription().contains("Read"));
    }

    @Test
    public void findMatchingTasksWithNoMatchingKeywordTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("sleep"));
        taskList.addTask(new Todo("Read"));
        taskList.addTask(new Deadline("Submit report", "2023.08.31"));
        List<Task> matchingTasks = taskList.findMatchingTasks("meeting");
        assertEquals(0, matchingTasks.size());
    }

    @Test
    public void findMatchingTasksWithPartialKeywordTest() {
        TaskList taskList = new TaskList();
        taskList.addTask(new Todo("Get ready to sleep"));
        taskList.addTask(new Todo("Read"));
        taskList.addTask(new Deadline("Submit report", "2023.08.31"));
        List<Task> matchingTasks = taskList.findMatchingTasks("re");
        assertEquals(2, matchingTasks.size());
        assertTrue(matchingTasks.get(0).getDescription().contains("re"));
        assertTrue(matchingTasks.get(1).getDescription().contains("re"));
    }

    @Test
    public void tempStoreTaskWithValidTaskTest() {
        TaskList taskList = new TaskList();
        String taskDescription = "Buy groceries";
        taskList.tempStoreTask(taskDescription);
        assertEquals(taskDescription, taskList.getTempStoredTask());
    }

    @Test
    public void tempStoreTaskWithEmptyTaskTest() {
        TaskList taskList = new TaskList();
        String taskDescription = "";
        taskList.tempStoreTask(taskDescription);
        assertTrue(taskList.getTempStoredTask().isEmpty());
    }

    @Test
    public void tempStoreTaskWithMultipleTasksTest() {
        TaskList taskList = new TaskList();
        String taskDescription1 = "Get ready to sleep";
        String taskDescription2 = "Read a book";
        taskList.tempStoreTask(taskDescription1);
        taskList.tempStoreTask(taskDescription2);
        assertEquals(taskDescription2, taskList.getTempStoredTask());
        assertEquals(taskDescription2, taskList.getTempStoredTask());
    }
}

