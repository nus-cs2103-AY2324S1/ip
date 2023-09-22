import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.UnmarkCommand;
import duke.task.Todo;

public class UnmarkCommandTest {

    @Test
    public void executeUnmarkSuccess() throws DukeException {
        Todo toDoTask = new Todo("return book");
        toDoTask.markTaskCompleted();
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        Todo toDoTaskUncompleted = new Todo("return book");
        String successMessage = "OK, I've marked this task as not done yet." + "\n" + toDoTaskUncompleted;

        assertEquals(successMessage, new UnmarkCommand(1).execute(taskList, ui));
    }

    @Test
    public void executeUnmarkAlreadyUnmarkedExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        String successMessage = "OK, I've marked this task as not done yet." + "\n" + toDoTask;

        try {
            assertEquals(successMessage, new UnmarkCommand(1).execute(taskList, ui));
        } catch (Exception e) {
            assertEquals("Task has already been marked as uncompleted.", e.getMessage());
        }
    }

    @Test
    public void executeUnmarkIndexOutOfBoundsExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        String successMessage = "OK, I've marked this task as not done yet." + "\n" + toDoTask;

        try {
            assertEquals(successMessage, new UnmarkCommand(4).execute(taskList, ui));
        } catch (Exception e) {
            assertEquals("I'm sorry, the task number you have entered a number that exceeds the size "
                    + "of your task list.", e.getMessage());
        }
    }
}
