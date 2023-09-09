package bongo.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import bongo.helper.BongoException;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.Task;
import bongo.task.TaskList;

/**
 * A class for a FindCommand.
 */
public class FindCommand extends Command {
    private final String[] searchInput;
    private String searchParam;

    /**
     * A constructor for a FindCommand, with a searchInput.
     * @param searchInput The search parameter input.
     */
    public FindCommand(String[] searchInput) throws BongoException {
        assert searchInput.length > 0 : "Command should not be empty.";
        this.searchInput = searchInput;
        this.validateSearchInput();
    }

    private void validateSearchInput() throws BongoException {
        if (this.searchInput.length <= 1) {
            throw new BongoException("Search parameter cannot be empty.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < searchInput.length; i++) {
            sb.append(searchInput[i]);
        }
        this.searchParam = sb.toString();
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filteredTaskList = tasks.getAllTasks().stream()
                .filter(currentTask -> currentTask.getDescription().contains(searchParam))
                .collect(Collectors.toCollection(ArrayList::new));

        return ui.showSearchedTasks(filteredTaskList);
    }
}
