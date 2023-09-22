package commands;

import data.Actions;
import duke.DukeException;
import tasks.Task;
import ui.UI;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void executeCommand(UI ui, Actions actionList) throws DukeException {
        StringBuilder matchedTasks = new StringBuilder();
        int counter = 0;
        for (int i = 0; i < actionList.size(); i++) {
            Task task = actionList.getAction(i);
            if (task.getDescription().contains(keyword)) {
                ++counter;
                matchedTasks.append(" ").append(counter).append(". ").append(task).append("\n");
            }
        }
        if (counter == 0) {
            ui.lineSandwich(" No Matches -> " + keyword);
        } else {
            ui.lineSandwich(" Check out the matches:\n" + matchedTasks);
        }
    }
}
