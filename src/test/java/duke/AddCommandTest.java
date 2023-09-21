package duke;

import duke.command.AddCommand;
import duke.command.DukeException;
import duke.task.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    @Test
    public void addDeadlineTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        String input = "deadline Submit assignment /by 2022-11-30";
        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(taskList, new Ui(), new Storage("./src/test/java/duke/sample.txt"));
        assertEquals(" Got it. I've added this task:\n" +
                "   [D][ ][L] Submit assignment (by: Nov 30 2022)\n" +
                " Now you have 1 tasks in the list.\n", result);
    }

    @Test
    public void addEventTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        String input = "event Submit assignment /at 2022-11-30";
        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(taskList, new Ui(), new Storage("./src/test/java/duke/sample.txt"));
        assertEquals(" Got it. I've added this task:\n" +
                "   [E][ ][L] Submit assignment (at: Nov 30 2022)\n" +
                " Now you have 1 tasks in the list.\n", result);
    }

    @Test
    public void addTodoTaskTest() throws DukeException {
        TaskList taskList = new TaskList();
        String input = "todo Submit assignment /from 2022-11-30 /to 2022-12-30";
        AddCommand addCommand = new AddCommand(input);
        String result = addCommand.execute(taskList, new Ui(), new Storage("./src/test/java/duke/sample.txt"));
        assertEquals(" Got it. I've added this task:\n" +
                "   [T][ ][L] Submit assignment (from: Nov 30 2022 to: Dec 30 2022)\n" +
                " Now you have 1 tasks in the list.\n", result);
    }

}
