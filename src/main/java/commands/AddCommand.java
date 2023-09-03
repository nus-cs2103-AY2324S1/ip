package commands;

import exceptions.JamesBondException;
import storage.Storage;
import tasks.Deadline;
import tasks.Task;
import tasks.ToDo;
import tasks.*;
import ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;
    private String type;

    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    public AddCommand(String description, LocalDateTime by) {
        this.description = description;
        this.type = "deadline";
        this.to = by;
    }

    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.type = "event";
        this.from = from;
        this.to = to;
    }

    @Override
    public void runCommand(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        switch (this.type) {
            case "todo":
                task = new ToDo(this.description);
                tasks.add(task);
                ui.todoMessage(task);
                break;
            case "deadline":
                task = new Deadline(this.description, this.to);
                tasks.add(task);
                ui.DeadlineMessage(task);
                break;
            case "event":
                task = new Event(this.description, this.from, this.to);
                tasks.add(task);
                ui.eventMessage(task);

                break;
        }
    }
}
