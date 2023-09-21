package duke.command;

import java.time.LocalDateTime;
import java.util.ArrayList;

import duke.components.DukeException;
import duke.components.Storage;
import duke.components.TaskList;
import duke.components.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

/**
 * Class of commands that adds to the existing list.
 */
public class AddCommand extends Command {
    /**
     * type contains the type of the command.
     *
     * task contains the name of the Task.
     *
     * deadline contains the by of the Task.
     *
     * from contains the start of the Task.
     *
     * to contains the end of the Task.
     *
     * isExit contains whether to terminate the bot.
     */
    private String type;
    private String task;
    private LocalDateTime deadline;
    private LocalDateTime from;
    private LocalDateTime to;
    private boolean isExit = false;
    private ArrayList<String> tags = new ArrayList<>();

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of the Task.
     * @param tags The list of tags.
     */
    public AddCommand(String type, String task, ArrayList<String> tags) {
        this.type = type;
        this.task = task;
        this.tags = tags;
    }

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of the Task.
     * @param deadline The by of the Task.
     * @param tags The list of tags.
     */
    public AddCommand(String type, String task, LocalDateTime deadline, ArrayList<String> tags) {
        this.type = type;
        this.task = task;
        this.deadline = deadline;
        this.tags = tags;
    }

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of Task.
     * @param from The start of Task.
     * @param to The end of Task.
     * @param tags The list of tags.
     */
    public AddCommand(String type, String task, LocalDateTime from, LocalDateTime to, ArrayList<String> tags) {
        this.type = type;
        this.task = task;
        this.from = from;
        this.to = to;
        this.tags = tags;
    }

    /**
     * Executes the command based on user input.
     *
     * @param tasks   List of tasks in taskList.
     * @param ui      Instance of the user interface.
     * @param storage Instance of the storage.
     * @return String in response to user input.
     * @throws DukeException If input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task tasking;
        switch (type) {
        case "T": {
            tasking = new Todo(task, false, tags);
            break;
        }
        case "D": {
            tasking = new Deadline(task, deadline, false, tags);
            break;
        }
        case "E": {
            tasking = new Event(task, from, to, false, tags);
            break;
        }
        default:
            return null;
        }
        tasks.addTask(tasking);
        storage.saveTasks(tasks.getTasks());
        return ui.showTaskAdded(tasking, tasks.getSize());
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
