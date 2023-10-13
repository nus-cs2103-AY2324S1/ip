package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    /*
        The index of task to be deleted.
     */
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {

        String result = "Noted. I've removed this task:" + "\n  " + tasks.get(this.index - 1);
        tasks.remove(this.index - 1);
        storage.writeInto(tasks);
        result += "\nNow that you have " + tasks.size() + (tasks.size() < 2 ? " task" : " tasks") + " in the list.";
        result += "\n";
        return result;
    }
}
