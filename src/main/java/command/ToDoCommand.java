package command;

import duke.TaskList;
import duke.Ui;
import task.ToDo;

public class ToDoCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {
        String desc = input.substring(5);
        String result;

        if (desc.length() == 0) {
            result = "☹ OOPS!!! The description of a todo cannot be empty.";
        } else {
            tasks.addTask(new ToDo(desc));
            result = ui.toDoSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
        }

        return result;
    }
}
