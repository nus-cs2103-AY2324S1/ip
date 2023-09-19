import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.MarkCommand;
import duke.task.Todo;

public class MarkCommandTest {
    @Test
    public void executeMarkSuccess() throws DukeException {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        Todo toDoTaskCompleted = new Todo("return book");
        toDoTaskCompleted.markTaskCompleted();
        String successMessage = "Nice! I've marked this task as done:" + "\n" + toDoTaskCompleted;

        assertEquals(successMessage, new MarkCommand(1).execute(taskList, ui));
    }
    @Test
    public void executeMarkAlreadyMarkedExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        toDoTask.markTaskCompleted();
        String successMessage = "Nice! I've marked this task as done:" + "\n" + toDoTask;
        try {
            assertEquals(successMessage, new MarkCommand(1).execute(taskList, ui));
        } catch (Exception e) {
            assertEquals("Task has already been marked as completed.", e.getMessage());
        }
    }
    @Test
    public void executeMarkIndexOutOfBoundsExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        toDoTask.markTaskCompleted();
        String successMessage = "Nice! I've marked this task as done:" + "\n" + toDoTask;
        try {
            assertEquals(successMessage, new MarkCommand(3).execute(taskList, ui));
        } catch (Exception e) {
            assertEquals("I'm sorry, the task number you have entered a number that exceeds the size of "
                    + "your task list.", e.getMessage());
        }
    }
}
