import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.UnmarkCommand;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnmarkCommandTest {

    @Test
    public void execute_unmark_success() throws DukeException {
        Todo toDoTask = new Todo("return book");
        toDoTask.markTaskCompleted();
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        Todo toDoTaskUncompleted = new Todo("return book");
        String successMessage = ui.showUnmarkMessage(toDoTaskUncompleted);

        assertEquals(successMessage, new UnmarkCommand(1).execute(taskList, ui));
    }

    @Test
    public void execute_unmark_alreadyUnmarkedExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        String successMessage = ui.showUnmarkMessage(toDoTask);

        try {
            assertEquals(successMessage, new UnmarkCommand(1).execute(taskList, ui));
        } catch (Exception e){
            assertEquals("Task has already been marked as uncompleted.", e.getMessage());
        }
    }

    @Test
    public void execute_unmark_indexOutOfBoundsExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();
        String successMessage = ui.showUnmarkMessage(toDoTask);

        try {
            assertEquals(successMessage, new UnmarkCommand(4).execute(taskList, ui));
        } catch (Exception e){
            assertEquals("I'm sorry, the task number you have entered a number that exceeds the size " +
                    "of your task list.", e.getMessage());
        }
    }
}
