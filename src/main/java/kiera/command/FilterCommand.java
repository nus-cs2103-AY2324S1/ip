package kiera.command;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.task.Task;
import kiera.tasktype.TaskType;

/**
 * Command that filters task based on input.
 */
public class FilterCommand extends Command {

    /**
     * Constructor for FilterCommand.
     *
     * @param t Type of tasks to be filtered.
     * @param desc Description that tasks are filtered by.
     */
    public FilterCommand(TaskType t, String desc) {
        setDescription(desc);
        setTaskType(t);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        TaskType t = this.getTaskType();
        LocalDate d = LocalDate.parse(this.getDescription());
        ArrayList<Task> filtered = new ArrayList<>();
        switch (t) {
        case DEADLINE:
            filtered = tasks.filterByDate(TaskType.DEADLINE, d);
            break;
        case EVENT:
            filtered = tasks.filterByDate(TaskType.EVENT, d);
            break;
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

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
