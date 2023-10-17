package tasks;

import ekud.exceptions.EkudIOException;
import ekud.exceptions.EkudInvalidCommandException;
import ekud.tasks.Priority;
import ekud.tasks.TaskList;
import ekud.tasks.TaskType;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void deleteTask_emptyTaskList_throwsException() {
        assertThrows(EkudInvalidCommandException.class,
            () -> {
                TaskList taskList = new TaskList();
                taskList.deleteTask(0);
            });
    }
    @Test
    public void addEvent_validFormat_success() {
        assertAll(() -> {
            TaskList taskList = new TaskList();
            taskList.addEvent(
                    "test event",
                    LocalDateTime.of(2023, 8, 10, 17, 30),
                    LocalDateTime.of(2023, 8, 10, 21, 30)
            );
        });
    }
    @Test
    public void addSavedTodoTask_validFormat_success() {
        assertAll(() -> {
            TaskList taskList = new TaskList();
            taskList.addSavedTask(
                    TaskType.TODO,
                    "test description",
                    Priority.HIGH,
                    new String[0]);
        });
    }
    @Test
    public void addSavedTodoEvent_missingDatetime_throwsEkudIOException() {
        assertThrows(EkudIOException.class,
            () -> {
                TaskList taskList = new TaskList();
                taskList.addSavedTask(
                        TaskType.EVENT,
                        "test description",
                        Priority.HIGH,
                        new String[0]);
            });
    }
    @Test
    public void findTasks_emptyTaskList_runsWithoutException() {
        assertAll(() -> {
            TaskList taskList = new TaskList();
            taskList.findTasks("test");
        });
    }
}
