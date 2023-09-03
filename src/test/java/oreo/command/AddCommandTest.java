package oreo.command;

import oreo.task.TaskList;
import oreo.task.ToDo;
import oreo.ui.Ui;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class AddCommandTest {
    Ui ui = new Ui();
    TaskList tasks = new TaskList();


    @Test
    public void execute_addCommandTodoToTaskList_success() {
        String description = "read book";
        Scanner tokeniser = new Scanner(description);
        AddCommand ac = new AddCommand("todo", tokeniser);
        ac.execute(ui, tasks);
        assertEquals(tasks.get(0).toString(),
                new ToDo("read book").toString());
    }

    @Test
    public void execute_addCommandInvalidInput_tasksNoChange() {
        String description = "read book";
        Scanner tokeniser = new Scanner(description);
        AddCommand ac = new AddCommand("abc", tokeniser);
        ac.execute(ui, tasks);
        assertEquals(0,
                tasks.getNumberOfTask());
    }
}
