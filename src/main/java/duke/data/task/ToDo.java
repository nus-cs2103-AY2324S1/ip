package duke.data.task;
/** The ToDo class is a child class of Task */
public class ToDo extends Task {

    /**
     * Constructor to initialize ToDo.
     *
     * @param description Description of the event.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String saveString() {
        return "T" + super.saveString() + "\r\n";
    }

    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
