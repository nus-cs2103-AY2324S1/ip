package haste.tasks;

public class ToDo extends Task {
    public ToDo(String description, boolean isComplete) {
        super(description, isComplete);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String save() {
        return "T|" + super.save();
    }
}

