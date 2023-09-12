package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;
import kiera.task.Deadline;
import kiera.task.Event;
import kiera.task.Task;
import kiera.tasktype.TaskType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Command that sorts tasks based on date.
 */
public class SortCommand extends Command {

    private TaskType t;
    private String comparable;

    public SortCommand(TaskType t, String comparable) {
        this.t = t;
        this.comparable = comparable;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> filtered;
        switch(t) {
        case DEADLINE:
            filtered = tasks.getTasks().stream()
                    .filter(task -> task instanceof Deadline)
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        case EVENT:
            filtered = tasks.getTasks().stream()
                    .filter(task -> task instanceof Event)
                    .collect(Collectors.toCollection(ArrayList::new));
            break;
        default:
            filtered = tasks.getTasks();
        }

        if (comparable.equals("date")) {
            filtered.sort(new CompareTasks("date"));
        } else {
            assert comparable.equals("time");
            filtered.sort(new CompareTasks("time"));
        }
        String result = filtered.stream()
                .map(task ->tasks.indexOf(task) + ". " + task + "\n")
                .collect(Collectors.joining());
        ui.showSortedList(t, comparable, result);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
