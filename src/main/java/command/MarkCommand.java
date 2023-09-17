package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class MarkCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {

        String result;
        try {
            int index = Integer.parseInt(input.substring(5));
            tasks.markTaskDone(index - 1);
            result = ui.markSuccess(tasks.getTasks(index - 1));
        } catch (NumberFormatException err) {
            result = "☹ OOPS!!! The number input does not exist.";
        } catch (DukeException err) {
            result = err.getMessage();
        }

        return result;
    }
}
