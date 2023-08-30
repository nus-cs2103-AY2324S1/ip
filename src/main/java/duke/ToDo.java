package duke;

import duke.InvalidInputException;
import duke.Storage;
import duke.Task;

public class ToDo extends Task {
    private String symbol = "[T]";
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return this.symbol + this.getCheckbox() + this.getName();
    }

    @Override
    public String newFormat() {
        return this.symbol + " | " + this.getInt() + " | " + this.getName();
    }

    @Override
    public void execute(TaskList lst, Ui ui, Storage storage) {
        lst.add(this);
        this.confirmation(lst.size());
        try {
            storage.saveTasks(lst);
        } catch (InvalidInputException e) {
            ui.printException(e.getMessage());
        }
    }
}