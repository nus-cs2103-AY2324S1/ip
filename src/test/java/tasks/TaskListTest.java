package tasks;

import exceptions.EkudIllegalArgException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TaskListTest {
    @Test
    public void deleteTask_emptyTaskList_throwsException() {
        assertThrows(EkudIllegalArgException.class,
                () -> {
                    TaskList taskList = new TaskList();
                    taskList.deleteTask(0);
                });
    }
    @Test
    public void addEvent_successful() {
        assertAll(() -> {
            TaskList taskList = new TaskList();
            taskList.addEvent(
                    "test event",
                    LocalDateTime.of(2023, 8, 10, 17, 30),
                    LocalDateTime.of(2023, 8,10, 21, 30)
            );
        });
    }
}
