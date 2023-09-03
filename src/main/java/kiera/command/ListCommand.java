package kiera.command;

import java.util.stream.Collectors;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;

/**
 * Command to display all tasks in list.
 */
public class ListCommand extends Command {
    public ListCommand() {
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            System.out.println("nothing for you to do yet!");
        }
        String result = tasks.getTasks().stream()
                .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                .collect(Collectors.joining());
        ui.showList(result);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
