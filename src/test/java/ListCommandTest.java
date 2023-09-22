import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import duke.TaskList;
import duke.Ui;
import duke.command.ListCommand;
import duke.task.Todo;

public class ListCommandTest {

    @Test
    public void listTasksSuccess() {
        Todo toDoTask = new Todo("return book");
        Todo toDoTask2 = new Todo("run 5km");
        ArrayList<String> taskListString = new ArrayList<>();
        TaskList taskList = new TaskList(taskListString);
        taskList.addTask(toDoTask);
        taskList.addTask(toDoTask2);
        Ui ui = new Ui();

        String successMessage = "Here are the tasks on your list: " + "\n"
                + "1. " + toDoTask + "\n" + "2. " + toDoTask2;

        assertEquals(successMessage, new ListCommand().execute(taskList, ui));
    }
}
