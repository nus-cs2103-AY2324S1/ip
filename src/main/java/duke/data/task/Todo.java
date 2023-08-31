package duke.data.task;
public class Todo  extends Task {
    /**
     * Constructor to initialize Todo.
     *
     * @param description Description of the Todo task.
     */
    public Todo (String description) {
        super(description);
//        if (description.isEmpty()) {
//            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
//        }
    }

    @Override
    public String toWrite() {
        return "T | " + super.toWrite() + "\n";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
