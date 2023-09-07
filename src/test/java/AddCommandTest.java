import duke.TaskList;
import duke.Ui;
import duke.command.AddCommand;
import duke.messages.Messages;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AddCommandTest {
    @Test
    public void execute_addToDo_success(){
        Todo toDoTask = new Todo("return book");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        Ui ui = new Ui();
        String successMessage = Messages.TASK_ADDED.getMessage() + "\n" + toDoTask + "\n" +
                "Now you have 1 tasks in the list.";

        assertEquals(successMessage, new AddCommand(toDoTask).execute(taskList, ui));
    }
}
