package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String keyword = Parser.extractKeyword(input);
        ArrayList<Task> matchingTasks = tasks.findTasksByKeyword(keyword);
        return ui.showMatchingTasks(matchingTasks);
    }
}
