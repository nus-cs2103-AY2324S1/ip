import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.DeleteCommand;
import duke.task.Todo;



public class DeleteCommandTest {
    @Test
    public void executeDeleteSuccess() throws DukeException {
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
    public void executeDeleteIndexOutOfBoundsExceptionThrown() {
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
