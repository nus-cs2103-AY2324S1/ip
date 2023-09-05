package duke;
/**
 * To-Do class that has 2 fields, its symbol and name
 */
public class ToDo extends Task {
    private final String SYMBOL = "[T]";

    /**
     * Constructor for a To-Do task
     * @param name name of the To-Do object
     */
    public ToDo(String name) {
        super(name);
    }
    @Override
    public String toString() {
        return this.SYMBOL + this.getCheckbox() + this.getName();
    }

    @Override
    public String newFormat() {
        return this.SYMBOL + " | " + this.getInt() + " | " + this.getName();
    }

//    @Override
//    public String execute(TaskList lst, Ui ui, Storage storage) {
//        lst.add(this);
//        try {
//            storage.saveTasks(lst);
//        } catch (InvalidInputException e) {
//            ui.printException(e.getMessage());
//        }
//        return this.confirmation(lst.size());
//    }
}
