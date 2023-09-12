package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        String result = "";
        tasks.get(this.index - 1).mark();
        result += Ui.showLine() + "\n" + " Nice! I've marked this task as done:" + "\n" + " " + tasks.get(this.index - 1).toString();
        result += "\n";
        result += Ui.showLine();
        storage.writeInto(tasks);
        return result;
    }
}
