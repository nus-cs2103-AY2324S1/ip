package duke.command;
import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private String type;
    private String task;
    private LocalDateTime deadline;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isExit = false;


    public AddCommand(String type, String task) {
        this.type = type;
        this.task = task;
    }

    public AddCommand(String type, String task, LocalDateTime deadline) {
        this.type = type;
        this.task = task;
        this.deadline = deadline;
    }

    public AddCommand(String type, String task, LocalDateTime from, LocalDateTime to) {
        this.type = type;
        this.task = task;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (type.equals("T")) {
            Task tasking = new Todo(task);
            tasks.addTask(tasking);
            ui.showTaskAdded(tasking, tasks.getSize());
        } else if (type.equals("D")) {
            Task tasking = new Deadline(task,deadline);
            tasks.addTask(tasking);
            ui.showTaskAdded(tasking, tasks.getSize());
        } else if (type.equals("E")) {
            Task tasking = new Event(task, from, to);
            tasks.addTask(tasking);
            ui.showTaskAdded(tasking, tasks.getSize());
        }
        storage.saveTasks(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return isExit;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String getTask() {
        return task;
    }
}
