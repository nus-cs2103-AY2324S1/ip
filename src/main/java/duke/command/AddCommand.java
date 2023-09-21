package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;
import duke.task.ToDo;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.DukeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {
    private String type;
    private String description;
    private LocalDateTime start;
    private LocalDateTime end;

    public AddCommand(String description) {
        this.type = "todo";
        this.description = description;
    }

    public AddCommand(String description, LocalDateTime end) {
        this.type = "deadline";
        this.description = description;
        this.end = end;
    }

    public AddCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.type = "event";
        this.description = description;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the AddCommand, adding the specified task to the task list.
     *
     * @param tasks The task list to which the task will be added.
     * @return A message indicating that the task has been added, along with the updated task count.
     * @throws DukeException If an invalid command is encountered.
     */
    @Override
    public String execute(TaskList tasks) throws DukeException {
        Task task;

        switch (this.type) {
        case "todo":
            task = new ToDo(this.description);
            break;
        case "deadline":
            task = new Deadline(this.description, this.end);
            break;
        case "event":
            task = new Event(this.description, this.start, this.end);
            break;
        default:
            throw new DukeException("I have no idea what that means...");
        }

        tasks.addTask(task);
        String addMessage = Ui.addTask(task);

        return addMessage + "\n" + Ui.countTasks(tasks);
    }
}
