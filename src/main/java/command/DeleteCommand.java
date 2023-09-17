package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

public class DeleteCommand {

    public static String process(String input, TaskList tasks, Ui ui) {

        String result;
        try {
            int index = Integer.parseInt(input.substring(7));
            Task removedTask = tasks.removeTask(index - 1);
            result = ui.deleteSuccess(removedTask, tasks.getSize());
        } catch (NumberFormatException err) {
            result = "☹ OOPS!!! The number input does not exist.";
        } catch (DukeException e) {
            result = e.getMessage();
        }

        return result;
    }
}
