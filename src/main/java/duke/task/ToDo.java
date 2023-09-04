package duke.task;

public class ToDo extends Task {
    public ToDo(String details) {
        super(details);
        super.setTaskType(TaskType.T.toString());
    }
}
