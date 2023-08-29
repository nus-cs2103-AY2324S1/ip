package dook.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task (String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    public abstract String getSaveableString();

    /**
     * Checks if description contains query.
     * @param query String to be queried for.
     * @return Whether description contains the word.
     */
    public boolean descriptionContains(String query) {
        String[] split = this.description.split(" ");
        for (String word : split) {
            if (word.equals(query)) {
                return true;
            }
        }
        return false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


}
