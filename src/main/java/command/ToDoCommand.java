package command;

import duke.TaskList;
import duke.Ui;
import task.ToDo;

public class ToDoCommand implements Command{

    public static String process(String input, TaskList tasks, Ui ui) {
        String description = input.substring(5);
        String result;

        if (description.length() == 0) {
            result = "☹ OOPS!!! The description of a todo cannot be empty.";
        } else {
            tasks.addTask(new ToDo(description));
            result = ui.toDoSuccess(tasks.getTasks(tasks.getSize() - 1), tasks.getSize());
        }

        return result;
    }
}
