package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {

    public FindCommand(ArrayList<String> commandDetails) {
        super(commandDetails);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        TaskList resultList = new TaskList();
        String keyword = commandDetails.get(0);
        for (int i = 0; i < tasks.size(); i++) {
            Task cur = tasks.get(i);
            if (tasks.get(i).getDescription().indexOf(keyword) != -1) {
                resultList.add(cur);
            }
        }
        ui.printFind(resultList);
    }
}
