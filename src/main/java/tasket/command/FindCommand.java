package tasket.command;

import java.util.ArrayList;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.storage.Storage;
import tasket.ui.Ui;

public class FindCommand extends Command {

    public FindCommand(String description) {
        super(description);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws TasketException {
        ArrayList<String> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            String taskString = String.format("%d.%s", i + 1, taskList.getTaskString(i));
            if (taskString.contains(commandDescription)) {
                matchingTasks.add(taskString);
            }
        }

        ui.showMatchingTasks(matchingTasks.toArray(new String[0]));
    }
}
