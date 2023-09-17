package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class UnMarkCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {

        String result;
        try {
            int index = Integer.parseInt(input.substring(7));
            tasks.markTaskUndone(index - 1);
            result = ui.unMarkSuccess(tasks.getTasks(index - 1));
        } catch (NumberFormatException err) {
            result = "☹ OOPS!!! The number input does not exist.";
        } catch (DukeException e) {
            result = e.getMessage();
        }

        return result;
    }
}
