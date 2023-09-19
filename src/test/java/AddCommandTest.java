import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;
import duke.command.AddCommand;
import duke.task.Todo;

public class AddCommandTest {
    @Test
    public void executeAddToDoSuccess() {
        Todo toDoTask = new Todo("return book");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        Ui ui = new Ui();
        String successMessage = "Got it. I've added this task:" + "\n" + toDoTask + "\n"
                + "Now you have 1 tasks in the list.";

        assertEquals(successMessage, new AddCommand(toDoTask).execute(taskList, ui));
    }
}
