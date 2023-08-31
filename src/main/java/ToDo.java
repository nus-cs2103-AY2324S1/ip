public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toTxtString() {
        return "[T] | [" + (this.isDone ? "X" : " ") + "] | " + this.description;

    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
