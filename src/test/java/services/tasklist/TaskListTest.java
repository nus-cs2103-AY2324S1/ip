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
    // the following three test cases are testing the add method in TaskList.
    @Mock
    protected Storage repoMock;
    @InjectMocks
    protected TaskList taskList;

    public TaskListTest() {
        repoMock = mock(Storage.class);
        taskList = new TaskList(repoMock);
    }

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
}
