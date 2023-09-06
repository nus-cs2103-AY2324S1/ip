package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String searchField;

    public FindCommand(String searchField) {
        this.searchField = searchField;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        TaskList filteredTasks = tasks.filter(searchField);
        if (filteredTasks.size() > 0) {
            return "Ooga booga! These are the matching tasks in your list:" +
                    ui.getListString(filteredTasks);
        } else {
            return "No tasks found. OOGA BOOGA!!";
        }
    }
}