package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.task.Task;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterWordCommand extends Command {
    public FilterWordCommand(String desc) {
        setDescription(desc);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filtered;
        filtered = tasks.filterByKeyword(this.getDescription());
        if (filtered.isEmpty()) {
            System.out.println("    no task matches the keyword: " + this.getDescription());
            return;
        }
        String content = filtered.stream()
                .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                .collect(Collectors.joining())
                .stripTrailing();
        ui.showFilteredByKeywordNotice(this.getDescription(), content, filtered.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
