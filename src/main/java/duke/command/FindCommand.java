package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class FindCommand extends Command {

    String findStr;

    public FindCommand(String findStr) {
        this.findStr = findStr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskList foundTasks = tasks.find(findStr);
        if (foundTasks.getSize() > 0) {
            ui.print("Here are the matching tasks in your list:");
            for (int i = 0; i < foundTasks.getSize(); i++) {
                System.out.println("\t " + (i + 1) + "." + foundTasks.getTaskString(i + 1));
            }
        } else {
            ui.print("There are no tasks with the given keyword!");
        }
    }

    @Override
    public String getCommandType() {
        return "Find";
    }
}
