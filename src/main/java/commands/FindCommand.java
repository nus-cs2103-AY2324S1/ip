package commands;

import exceptions.DukeException;
import io.Storage;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

public class FindCommand extends Command {
    public FindCommand(String command) {
        super(command);
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String input = getCommand();
        String[] inputArray = input.split(" ");
        String keyword = inputArray[1];
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                output += (i + 1) + ". " + task.toString() + "\n";
            }
        }
        ui.showFind(output);
    }
}
