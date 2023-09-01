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

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of the Task.
     */
    public AddCommand(String type, String task) {
        this.type = type;
        this.task = task;
    }

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of the Task.
     * @param deadline The by of the Task.
     */
    public AddCommand(String type, String task, LocalDateTime deadline) {
        this.type = type;
        this.task = task;
        this.deadline = deadline;
    }

    /**
     * Constructor for AddCommand.
     *
     * @param type The type of command.
     * @param task The name of Task.
     * @param from The start of Task.
     * @param to The end of Task.
     */
    public AddCommand(String type, String task, LocalDateTime from, LocalDateTime to) {
        this.type = type;
        this.task = task;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the command based on user input.
     *
     * @param tasks List of tasks in taskList.
     * @param ui Instance of the user interface.
     * @param storage Instance of the storage.
     * @throws DukeException If input is invalid.
     */
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
}
