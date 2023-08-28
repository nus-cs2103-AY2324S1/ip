public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toTxt() {
        return super.toTxt() + this.description;
    }

}
