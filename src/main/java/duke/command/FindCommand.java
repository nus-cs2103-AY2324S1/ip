package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class FindCommand extends Command {

    private String find;
    public FindCommand(String find) {
        this.find = find;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // add task into tasks, input coming from Parse class, Parse class is supposed to make the String into a duke.task.Task
        // print in ui
        // write in storage
        ui.printFind(taskList.find(this.find));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
