public class Task {
    protected String taskName;
    protected boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getName() {
        return taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        completed = true;
    }

    public void setNotCompleted() {
        completed = false;
    }

    public String toFileString() {
        if (completed) {
            return "T | 1 | " + taskName;
        } else {
            return "T | 0 | " + taskName;
        }
    }

    public static Task fromFileString(String fileString) {
        String[] parts = fileString.split("\\|");
        String taskType = parts[0].trim();
        String isCompleted = parts[1].trim();
        String taskName = parts[2].trim();

        Task task;
        if (taskType.equals("T")) {
            task = new Todo(taskName);
        } else if (taskType.equals("D")) {
            String by = parts[3].trim();
            task = new Deadline(taskName, by);
        } else if (taskType.equals("E")) {
            String from = parts[3].trim();
            String to = parts[4].trim();
            task = new Event(taskName, from, to);
        } else {
            task = null;
        }

        if (task != null && isCompleted.equals("1")){
            task.setCompleted();
        }

        return task;
    }

    @Override
    public String toString() {
        char mark;
        if (completed) {
            mark = 'X';
        } else {
            mark = ' ';
        }

        return "[" + mark + "] " + taskName;
    }
}
