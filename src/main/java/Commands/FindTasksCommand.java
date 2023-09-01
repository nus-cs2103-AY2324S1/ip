package Commands;

import Duke.DukeException;
import OOP.Storage;
import OOP.TaskList;
import OOP.Ui;
import Tasks.Task;

import java.util.List;
import java.util.stream.Collectors;

public class FindTasksCommand implements Command {
    private String searchText;
    public FindTasksCommand(String searchText) {
        this.searchText = searchText;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filteredTasks = tasks.getTasks().stream().filter(t -> t.toString().contains(searchText)).collect(Collectors.toList());
        // get UI to print out the filtered list of tasks
        int i = 1;
        ui.printFindTaskMessage();
        for (Task task : filteredTasks) {
            ui.printTask(i, task);
            i++;
        }
    }
}
