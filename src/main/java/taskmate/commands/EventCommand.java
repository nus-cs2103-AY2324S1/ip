package taskmate.commands;

import taskmate.tools.Storage;
import taskmate.tools.TaskList;
import taskmate.tools.Ui;
import taskmate.tools.tasks.Event;
import taskmate.tools.tasks.Task;

import java.time.LocalDate;

/**
 * The EventCommand class is the child class of the AddCommand class. It represents an "event" tasks given by the
 * user in the following form: "event <name> /from <date> /to <date>".
 */
public class EventCommand extends AddCommand {
    String commandType;
    boolean isExit;
    LocalDate from, to;

    /**
     * EventCommand constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, and a date that represents the date that this task ends. These dates must be
     * `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param from the date that the event task starts.
     * @param to the date that the event task ends.
     */
    EventCommand(String name, LocalDate from, LocalDate to) {
        this.commandType = "Event";
        this.isExit = false;
        this.name = name;
        this.from = from;
        this.to = to;
    }

    /**
     * EventCommand constructor that allows the developer to specify the name of the task, a date that represents
     * the date that this task starts, and a date that represents the date that this task ends. These dates must
     * be String instances in the form "YYYY-mm-dd" to be parsed to `LocalDate` instances.
     *
     * @param name the name of the event task.
     * @param from the date that the event task starts. It has to be of the form "YYYY-mm-dd".
     * @param to the date that the event task ends. It has to be of the form "YYYY-mm-dd".
     */
    public EventCommand(String name, String from, String to) {
        this.commandType = "Event";
        this.isExit = false;
        this.name = name;
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    /**
     * Executes the `event` command by the user by creating a new Event instance and adding it into the user's task
     * list, which is a TaskList instance `tasks`. Upon successful execution, a success message is printed by the `ui`
     * instance to inform the user about the successful execution.
     *
     * @param tasks TaskList object that stores the list of undeleted tasks defined by the user.
     * @param ui Ui object that deals with taking in user input and printing messages out to the user.
     * @param storage Storage object that saves undeleted tasks to the disk.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // 1. Create new deadline task
        Task newEventTask = new Event(name, from, to);

        // 2. Add to newTodoTask to tasks
        tasks.addTask(newEventTask);

        // 3. Print successful task added message
        ui.printSuccessfulAddTaskResponse(newEventTask, tasks.getNumTotalTasks());
    }
}
