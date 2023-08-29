package shiba.commands;

import shiba.exceptions.InvalidCommandException;
import shiba.exceptions.ShibaException;
import shiba.tasks.PersistentTaskList;
import shiba.tasks.ShibaTask;
import shiba.ui.Replier;

import java.util.List;

public class FindCommand extends ShibaCommand {
    private final String fullCmd;

    public FindCommand(PersistentTaskList tasks, String cmd) {
        super(tasks);
        fullCmd = cmd;
    }

    @Override
    public void execute() throws ShibaException {
        String[] params = fullCmd.split(" ", 2);
        if (params.length < 2) {
            throw new InvalidCommandException("Please enter a keyword to search for!");
        }

        String keyword = params[1];
        Replier.printHorizontalLine();
        List<ShibaTask> matchingTasks = tasks.findTasksWithKeyword(keyword);
        if (matchingTasks.isEmpty()) {
            Replier.printWithLevel2Indent("Woof! No tasks containing keyword found!");
        } else {
            Replier.printWithLevel2Indent("Woof! Here are the tasks containing the keyword!");
            for (int i = 0; i < matchingTasks.size(); i++) {
                Replier.printWithLevel2Indent((i + 1) + "." + matchingTasks.get(i));
            }
        }
        Replier.printHorizontalLine();
    }
}
