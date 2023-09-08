package duke.commands;

import duke.TaskList;
import duke.Ui;
import duke.ToDo;
import duke.Task;
import duke.Event;
import duke.Deadline;
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

    private static LocalDateTime formatDateTime(String input) throws DateTimeParseException {
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }
}
