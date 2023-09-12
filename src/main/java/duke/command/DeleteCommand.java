package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        tasks.remove(this.index - 1);
        storage.writeInto(tasks);
        String result = Ui.showLine() + "\n" + "Noted. I've removed this task:" + "\n  " + tasks.get(this.index - 1);
        result += "\nNow that you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.";
        result += "\n";
        result += Ui.showLine();
        return result;
    }
}
