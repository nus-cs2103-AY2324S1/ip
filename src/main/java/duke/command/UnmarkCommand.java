package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        String result = "";
        tasks.get(this.index - 1).unmark();
        result += Ui.showLine() + "\n" + " Ok! I've marked this task as not done yet:" + "\n" + " " + tasks.get(this.index - 1).toString();
        result += "\n";
        result += Ui.showLine();
        storage.writeInto(tasks);
        return result;
    }
}
