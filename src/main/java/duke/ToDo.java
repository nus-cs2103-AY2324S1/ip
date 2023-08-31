package duke;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String writeToFile() {
        int mark;
        if (super.getStatusIcon() == "X") {
            mark = 1;
        } else {
            mark = 0;
        }
        return "T | " + mark + " | " + super.writeToFile();
    }
}
