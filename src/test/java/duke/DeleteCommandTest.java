package duke;

import duke.command.DeleteCommand;
import duke.command.DukeException;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    @Test
    public void deleteTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        TodoTask todoTask = new TodoTask("Read a book", LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.MEDIUM);
        taskList.addTask(todoTask);

        String input = "delete 1";
        DeleteCommand deleteCommand = new DeleteCommand(input);
        String result = deleteCommand.execute(taskList, new Ui(), new Storage("./src/test/java/duke/sample.txt"));

        assertEquals(" Noted. I've removed this task:\n" +
                "   [T][ ][M] Read a book (from: Oct 15 2019 to: Dec 01 2020)\n" +
                " Now you have 0 tasks in the list.\n", result);
    }
}
