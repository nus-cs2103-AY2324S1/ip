package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {

    /*
        The index of task to be marked.
     */
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        String result = "";
        tasks.get(this.index - 1).mark();
        result += "Nice! I've marked this task as done:" + "\n" + " " + tasks.get(this.index - 1).toString();
        result += "\n";
        storage.writeInto(tasks);
        return result;
    }
}
