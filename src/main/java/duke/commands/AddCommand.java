package duke.commands;

import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private String description;
    private LocalDateTime till;
    private LocalDateTime from;
    private String type;

    // ToDo Constructor
    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    // duke.tasks.Deadline Constructor
    public AddCommand(String description, LocalDateTime till) {
        this.description = description;
        this.till = till;
        this.type = "deadline";
    }

    // duke.tasks.Event Constructor
    public AddCommand(String description, LocalDateTime from, LocalDateTime till) {
        this.description = description;
        this.till = till;
        this.from = from;
        this.type = "event";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = null;
        switch (this.type) {
            case "todo":
                task = new Todo(this.description);
                break;
            case "deadline":
                task = new Deadline(this.description,this.till);
                break;
            case "event":
                task = new Event(this.description, this.from, this.till);
                break;
        }
        tasks.add(task);
        ui.showAdd(tasks.size(), task);
        storage.writeData(tasks.getAllTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
