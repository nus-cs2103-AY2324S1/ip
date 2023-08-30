package duke.data.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toSaveDataFormat() {
        return String.format("T | %d | %s", isDone() ? 1 : 0, getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ToDo) {
            ToDo otherToDo = (ToDo) other;
            return (this.description.equals(otherToDo.description));
        } else {
            return false;
        }
    }
}
