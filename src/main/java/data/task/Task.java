package data.task;

public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    public abstract String getType();

    public abstract String getDateTime();

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        }
        return "[ ] " + description;
    }

    // Need to store type of task, whether it is done, title, date/time
    public String toFileString() {
        StringBuilder output = new StringBuilder();
        String type = this.getType();
        String completed;
        if (this.isDone) {
            completed = "1";
        } else {
            completed = "0";
        }
        String title = this.description;
        String dateTime = this.getDateTime();
        if (this.getDateTime().length() == 0) {
            output.append(type + "," + completed + "," + title + "\n");
        }
        else {
            output.append(type + "," + completed + "," + title + "," + dateTime + "\n");
        }
        return output.toString();
    }
}
