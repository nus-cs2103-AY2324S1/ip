public class ToDo extends Task {
    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    public ToDo(String todoDesc, boolean isDone) {
        super(todoDesc, isDone);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
