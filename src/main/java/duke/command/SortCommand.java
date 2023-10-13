package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.EmptyDescriptionException;
import duke.exception.NoSuchCommandException;
import duke.task.Task;
import duke.task.TaskList;

import java.util.Collections;
import java.util.Comparator;

public class SortCommand extends Command {

    public SortCommand(String detail) {
        super(detail);
    }

    @Override
    public String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException, NoSuchCommandException {

        String result;
        Comparator<Task> c;

        switch (this.detail.replace(" ", "")) {
        case "a":
            c = new Comparator<Task>() {
                @Override
                public int compare(Task t1, Task t2) {
                    return t1.getDescription().compareTo(t2.getDescription());
                }
            };
            result = "Your tasks are sorted alphabetically. \n";
            break;
        case "ra":
            c = new Comparator<Task>() {
                @Override
                public int compare(Task t1, Task t2) {
                    return t2.getDescription().compareTo(t1.getDescription());
                }
            };
            result = "Your tasks are sorted reverse-alphabetically. \n";
            break;
        case "":
            throw new EmptyDescriptionException("sort");
        default:
            throw new NoSuchCommandException();
        }
        Collections.sort(tasks.getTasks(), c);
        storage.writeInto(tasks);
        return result;
    }
}
