package duke;

import duke.command.DukeException;
import duke.command.FindCommand;
import duke.task.TaskList;
import duke.task.TaskPriority;
import duke.task.TodoTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindCommandTest {
    @Test
    public void findTasksByKeywordTest() throws DukeException {
        TaskList taskList = new TaskList();
        TodoTask todoTask1 = new TodoTask("Read book",  LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.MEDIUM);
        TodoTask todoTask2 = new TodoTask("Read magazine",  LocalDate.parse("2019-10-15"), LocalDate.parse("2020-12-01"), false, TaskPriority.LOW);
        taskList.addTask(todoTask1);
        taskList.addTask(todoTask2);

        String input = "find Read";
        FindCommand findCommand = new FindCommand(input);
        String result = findCommand.execute(taskList, new Ui(), new Storage("./src/test/java/duke/sample.txt"));

        assertEquals(" Here are the matching tasks in your list:\n" +
                " 1.[T][ ][M] Read book (from: Oct 15 2019 to: Dec 01 2020)\n" +
                " 2.[T][ ][L] Read magazine (from: Oct 15 2019 to: Dec 01 2020)\n", result);
    }
}
