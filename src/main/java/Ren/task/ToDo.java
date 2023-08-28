package ren.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
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

    public ToDo() {
        super();
        this.taskType = TaskType.TODO;
    }

    public ToDo(String todoDesc, boolean isDone) {
        super(todoDesc, isDone);
        this.taskType = TaskType.TODO;
    }

    public ToDo(String description, boolean isDone, TaskType taskType) {
        super(description, isDone);
        this.taskType = TaskType.TODO;
    }

    public ToDo(String type, String description, boolean isDone, TaskType taskType) {
        super(description, isDone);
        this.taskType = TaskType.TODO;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
