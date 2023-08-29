package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class FindCommand implements Command{
    private final String details;

    public FindCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getName().contains(details)) {
                output.append("\n").append(i + 1).append(".").append(tasks.get(i).toString());
            }
        }
        ui.sendMessage(output.toString());
    }

    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
