package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

public class TodoCommand extends Command {

    public TodoCommand(String detail) {
        super(detail);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        Task todo = new Todo(this.detail, false);
        tasks.add(todo);
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Got it. I've added this task: " + "\n";
        result += " " + todo + "\n" + "Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }
}
