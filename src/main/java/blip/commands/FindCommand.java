package blip.commands;

import java.time.LocalDateTime;
import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;

public class FindCommand extends Command {
    String description;
    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, BlipUI ui, BlipStorage storage) {
        if (tasks.size() == 0) {
            ui.showNoMatchingTasksMsg();
        }
        int numOfTasksMatched = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).toString().contains(description)) {
                if (numOfTasksMatched == 0) {
                    System.out.println("Here are the matching tasks in your list:");
                }
                System.out.println((i + 1) + "." + tasks.getTask(i).toString());
                numOfTasksMatched++;
            }
        }
        if (numOfTasksMatched == 0) {
            ui.showNoMatchingTasksMsg();
        }
    }
}
