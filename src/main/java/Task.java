public class Task {
    private boolean completed = false;
    private String taskName = "";

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void mark() {
        this.completed = true;
    }

    public void unmark() {
        this.completed = false;
    }

    /**
     * Converts Task object to its string representation when stored in the hard drive.
     *
     * @return String representation when stored in text file on user's hard drive
     */
    public String toData() {
        return (completed ? "1" : "0") + " | " + taskName;
    }

    @Override
    public String toString() {
        String checkbox = "[" + (completed ? "X" : " ") + "]";
        return checkbox + " " + taskName;
    }
}