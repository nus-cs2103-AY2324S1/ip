package oreo.task;

public class ToDo extends Task {

    /**
     * Constructor of ToDo Class.
     *
     * @param d task description
     */
    public ToDo(String d) {
        super(d);
    }

    /**
     * Constructor for ToDo Class specifically for file reading.
     *
     * @param description task description.
     * @param completed whether task is complete.
     */
    public ToDo(String description, boolean completed) {
        super(description);
        this.isComplete = completed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        String marker = "☐";
        if (this.isComplete) {
            marker = "☑";
        }
        return  "\uD83C\uDD83:" + " " + this.description + " " + marker + "\n";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String writeToFile() {
        int mark = isComplete ? 1 : 0;
        String data = 1 + " " + mark + description + System.lineSeparator();
        return data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskInEditFormat() {
        return "todo" + description;
    }
}
