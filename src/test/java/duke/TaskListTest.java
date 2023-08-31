package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    private static final Todo TODO = new Todo("read book");
    private static final Deadline DEADLINE = new Deadline("return book",
            LocalDateTime.parse("01 Sep 2023 - 16:00", Duke.TIME_FORMAT));
    private static final Event EVENT = new Event("project meeting",
            LocalDateTime.parse("06 Aug 2023 - 14:00", Duke.TIME_FORMAT),
            LocalDateTime.parse("06 Aug 2023 - 16:00", Duke.TIME_FORMAT));
    private static final TaskList TASK_LIST = TaskList.of(TODO, DEADLINE, EVENT);

    @Test
    public void addToList_addTask_addSuccessfully() {
        TaskList taskList = new TaskList();
        taskList.addToList(TODO);
        taskList.addToList(DEADLINE);
        taskList.addToList(EVENT);
        assertEquals(TASK_LIST, taskList);
    }

    @Test
    public void getTaskAt_correctIndex_getSuccessfully() {
        assertEquals(TODO, TASK_LIST.getTaskAt(0));
        assertEquals(DEADLINE, TASK_LIST.getTaskAt(1));
        assertEquals(EVENT, TASK_LIST.getTaskAt(2));
    }

    @Test
    public void deleteTaskAt_correctIndex_deleteSuccessfully() {
        TaskList taskList = new TaskList();
        taskList.addToList(TODO);
        taskList.addToList(DEADLINE);
        taskList.addToList(EVENT);
        taskList.addToList(new Todo("play tennis"));
        taskList.deleteTaskAt(3);
        assertEquals(TASK_LIST, taskList);
    }

    @Test
    public void getNumberOfTasks() {
        assertEquals(3, TASK_LIST.getNumberOfTasks());
    }
}
