import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.DeleteCommand;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void execute_delete_success() throws DukeException {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        String successMessage = "Noted. I've removed this task:" + "\n" + toDoTask + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(successMessage, new DeleteCommand(1).execute(taskList, ui));
    }
    @Test
    public void execute_delete_indexOutOfBoundsExceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        String successMessage = "Noted. I've removed this task:" + "\n" + toDoTask + "\n"
                + "Now you have 1 tasks in the list.";
        try {
            assertEquals(successMessage, new DeleteCommand(3).execute(taskList, ui));
        } catch (Exception e) {
            assertEquals("I'm sorry, the task number you have entered a number that exceeds the size "
                    + "of your task list.", e.getMessage());
        }
    }
}
