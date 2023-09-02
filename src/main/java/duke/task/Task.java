package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getTaskType() {
        return ""; // Return an empty string for the base class
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    public boolean getIsDone() {
        return this.isDone;
    }
    public String taskStatus() {
        return isDone ? "X" : " ";
    }

    public String toFileString() {
        char doneStatus = isDone ? '1' : '0';
        return doneStatus + " | " + description;
    }
    @Override
    public String toString() {
        return getTaskType() + " [" + taskStatus() + "] " + description;
    }
    public static Task createTaskFromData(String taskData) {
        String[] taskParts = taskData.split("\\s*\\|\\s*");

        if (taskParts.length >= 3) {
            String taskType = taskParts[0].trim();
            String doneStatus = taskParts[1].trim();
            String description = taskParts[2].trim();

            Task task;

            if (taskType.equals("T")) {
                task = Todo.createTodoFromData(taskData);
            } else if (taskType.equals("D")) {
                task = Deadline.createDeadlineFromData(taskData);
            } else if (taskType.equals("E")) {
                task = Event.createEventFromData(taskData);
            } else {
                System.out.println("Unrecognized task type: " + taskType);
                return null;
            }

            if (doneStatus.equals("1")) {
                task.markDone();
            }
            return task;
        }
        return null; // Incomplete data
    }
}
