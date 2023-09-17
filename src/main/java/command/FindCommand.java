package command;

import duke.TaskList;
import duke.Ui;

public class FindCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {

        String desc = input.substring(5);
        String result;

        if (desc.length() == 0) {
            result = "☹ OOPS!!! The description of find cannot be empty.";
        } else {
            result = ui.findSuccess(tasks.findTasks(desc));
        }

        return result;
    }
}
