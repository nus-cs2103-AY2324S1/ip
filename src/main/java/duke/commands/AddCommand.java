package duke.commands;

import duke.storage.Storage;
import duke.tasks.*;
import duke.ui.Ui;

import java.time.LocalDateTime;

public class AddCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime till;
    private String type;

    public AddCommand(String description) {
        this.description = description;
        this.type = "todo";
    }

    public AddCommand(String description, LocalDateTime by) {
        this.description = description;
        this.type = "deadline";
        this.till = by;
    }

    public AddCommand(String description, LocalDateTime from, LocalDateTime to) {
        this.description = description;
        this.type = "event";
        this.from = from;
        this.till = to;
    }

    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage){
        Task task = null;
        switch (this.type) {
            case "todo":
                task = new ToDo(this.description);
                break;
            case "deadline":
                task = new Deadline(this.description, this.till);
                break;
            case "event":
                task = new Event(this.description, this.from, this.till);
                break;
        }
        tasks.add(task);
        ui.acknowledgeAdd(tasks.getSize(), task);
        storage.updateStorage(tasks.getArrayList());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
