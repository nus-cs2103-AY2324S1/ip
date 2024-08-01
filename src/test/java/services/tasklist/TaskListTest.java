package services.tasklist;

import command.CommandType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import services.tasklist.tasks.Deadline;
import services.tasklist.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class TaskListTest {
    @Mock
    protected Storage repoMock;
    @InjectMocks
    protected TaskList taskList;

    public TaskListTest() {
        // creating a mock Storage object and injecting it into a TaskList object.
        repoMock = mock(Storage.class);
        taskList = new TaskList(repoMock);
    }

    // the following three test cases are testing the add method in TaskList.
    @Test
    public void add_todoTask_success() {
        try {
            taskList.addTask("test", CommandType.TODO);
            assertEquals(1, taskList.taskCount);
            assertTrue(taskList.tasks.get(0) instanceof Todo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add_deadlineTask_success() {
        try {
            taskList.addTask("test", CommandType.DEADLINE, "2020-08-25 00:00");
            assertEquals(1, taskList.taskCount);
            assertTrue(taskList.tasks.get(0) instanceof Deadline);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void add_eventTask_exceptionThrown() {
        try {
            taskList.addTask("test", CommandType.EVENT, "invalid time format", "2020-08-25 01:00");
        } catch (Exception e) {
            assertEquals("Sir, please check again to ensure you provide "
                    + "the correct arguments for command (event).", e.getMessage());
        }
    }

    // the following three test cases are testing the delete method in TaskList.
    @Test
    public void delete_existingTask_success() {
        try {
            taskList.tasks.add(new Todo("test"));
            taskList.tasks.add(new Deadline("test", "2020-08-25 00:00"));
            taskList.taskCount = 2;

            // delete the first task in the list. Only the deadline task should remain.
            taskList.deleteTask(1);
            assertEquals(1, taskList.taskCount);
            assertTrue(taskList.tasks.get(0) instanceof Deadline);

            // all tasks should be deleted.
            taskList.deleteTask(1);
            assertEquals(0, taskList.taskCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete_emptyTaskList_exceptionThrown() {
        try {
            taskList.deleteTask(1);
        } catch (Exception e) {
            assertEquals("Sir, your calendar does not contain this task index (1).\n"
                    + "It currently has 0 tasks.", e.getMessage());
        }
    }

    @Test
    public void delete_invalidTaskIndex_exceptionThrown() {
        try {
            taskList.tasks.add(new Todo("test"));
            taskList.tasks.add(new Deadline("test", "2020-08-25 00:00"));
            taskList.taskCount = 2;

            // delete the third task in the list, which does not exist.
            taskList.deleteTask(3);
        } catch (Exception e) {
            assertEquals("Sir, your calendar does not contain this task index (3).\n"
                    + "It currently has 2 tasks.", e.getMessage());
        }
    }

}
