package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to find tasks based on search string */
public class FindCommand implements Command {
    private final String stringToSearch;

    public FindCommand(String stringToSearch) {
        this.stringToSearch = stringToSearch;
    }

    /**
     * Finds and prints tasks based on search string.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        String[] splitString = stringToSearch.split(" ");
        OUTPUT.delete(0, OUTPUT.length());
        OUTPUT.append(taskList.print(splitString));
    }
}
