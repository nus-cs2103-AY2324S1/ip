package kiera.command;

import kiera.*;
import kiera.task.Task;
import kiera.tasktype.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterCommand extends Command {
    public FilterCommand(TaskType t, String desc) {
        setDescription(desc);
        setTaskType(t);
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskType t = this.getTaskType();
        LocalDate d = LocalDate.parse(this.getDescription());
        ArrayList<Task> filtered = new ArrayList<>();
        switch (t) {
            case DEADLINE:
                filtered = tasks.filterByDate(TaskType.DEADLINE, d);
            case EVENT:
                filtered = tasks.filterByDate(TaskType.EVENT, d);
            default:
                System.out.println("this task type has no date property!");
        }
        if (filtered.isEmpty()) {
            System.out.println("no " + t + " due on " + d);
            return;
        }
        String content = filtered.stream()
                .map(task -> "     " + tasks.indexOf(task) + ". " + task + "\n")
                .collect(Collectors.joining());
        ui.showFilteredNotice(d, t, content, filtered.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
