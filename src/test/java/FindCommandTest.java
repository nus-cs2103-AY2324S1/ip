import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.command.FindCommand;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {

    @Test
    public void find_task_success() throws DukeException {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        String successMessage = "Here are the matching tasks in your list" + "\n" + "1. " + toDoTask ;

        assertEquals(successMessage, new FindCommand("book").execute(taskList, ui));
    }

    @Test
    public void no_task_found_exceptionThrown() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        String successMessage = "Here are the matching tasks in your list" + "\n" + "1. " + toDoTask ;

        try {
            assertEquals(successMessage, new FindCommand("water").execute(taskList, ui));
        } catch (Exception e){
            assertEquals("I'm sorry, I could not find task with this word in it.", e.getMessage());
        }

    }
}