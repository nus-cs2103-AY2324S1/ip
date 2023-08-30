package duke;

/**
 * To-Do class that has 2 fields, its symbol and name
 */
public class ToDo extends Task {
    private String symbol = "[T]";

    /**
     * Constructor for a To-Do task
     * @param name name of the To-Do object
     */
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