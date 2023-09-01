package monke.commands;

import monke.MonkeException;
import monke.Storage;
import monke.TaskList;
import monke.Ui;
import monke.tasks.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    private String searchField;

    public FindCommand(String searchField) {
        this.searchField = searchField;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws MonkeException {
        TaskList filteredTasks = tasks.filter(searchField);
        if (filteredTasks.size() > 0) {
            ui.print("Ooga booga! These are the matching tasks in your list:");
            ui.displayList(filteredTasks);
        } else {
            ui.print("No tasks found. OOGA BOOGA!!");
        }
    }
}