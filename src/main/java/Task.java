/*
 * Abstract class that represents a general task.
 */
abstract class Task {
    private boolean isDone;
    private String task;

    public Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static boolean checkTaskNoDescription(String taskType, String task) {
        return taskType.equals(task);
    }

    public static boolean checkAllWhiteSpace(String s) {
        for (int i = 0; i < s.length(); i += 1) {
            if (s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public String stringToSave() {
        String status = isDone ? "[X]" : "[ ]";
        return " | " + status + " | " + task;
    }

    @Override
    public String toString() {
        String status = isDone ? "[X]" : "[ ]";
        return status + " " + task;
    }
}