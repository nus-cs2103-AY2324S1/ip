package slay.command;

import slay.task.*;
import slay.TaskList;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    private TaskList originalTaskList;
    private TaskList expectedTaskList;

    @BeforeEach
    public void setUp() throws Exception {
        ToDo todo = new ToDo("test_todo");
        Deadline deadline = new Deadline("test_deadline", "2222-02-22", "22:22");
        Event event = new Event("test_event", "2222-02-22", "22:22", "2222-02-22", "23:23");

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(todo);
        tasks.add(deadline);
        tasks.add(event);

        ArrayList<Task> tasksAfterDelete = new ArrayList<>();
        tasksAfterDelete.add(deadline);
        tasksAfterDelete.add(event);

        originalTaskList = new TaskList(tasks);
        expectedTaskList = new TaskList(tasksAfterDelete);
    }

    @Test
    public void execute_validIndex_taskDeleted() {
        DeleteCommand deleteCommand = new DeleteCommand(1);
        deleteCommand.setData(this.originalTaskList);
        CommandResult result = deleteCommand.execute();
        assertEquals(expectedTaskList.getTask(1), originalTaskList.getTask(1));
        assertEquals(expectedTaskList.getTask(2), originalTaskList.getTask(2));
    }

    @Test
    public void execute_inValidIndex_throwException() {
        DeleteCommand deleteCommand = new DeleteCommand(0);
        deleteCommand.setData(this.originalTaskList);
        CommandResult result = deleteCommand.execute();
        assertEquals("You don't seem to have so many tasks. Which task do you want to delete?",
                result.feedbackToUser);
    }
}