package commands;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;
import java.util.ArrayList;

/**
 * FindCommand class for command that asks to find tasks with given keyword
 */
public class FindCommand extends Command {

    private String keywordToFind;

    public FindCommand(String keyword) {
        this.keywordToFind = keyword;
    }
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ArrayList<Task> tasksWithKeyword = tasks.findTaskUsingKeyword(this.keywordToFind);
        return ui.printMatchingTasks(tasksWithKeyword);
    }
}
