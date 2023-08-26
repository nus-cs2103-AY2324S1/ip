package duke;
public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String save() {
        return "T|" + super.status();
    }

}
