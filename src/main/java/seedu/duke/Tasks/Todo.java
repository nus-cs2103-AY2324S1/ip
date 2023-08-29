package seedu.duke.Tasks;

public class Todo extends Task {
    public Todo(String description, boolean isMarked) {
        super(description, isMarked); // initializes its task
    }

    public String writeFormat() {
        int isDone = 0;
        if (super.isMarked()) {
            isDone = 1;
        }
        return "T" + " | " + isDone + " | " + super.description;
    };

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
