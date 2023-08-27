public class Deadline extends Task{

    protected String limit;

    private Deadline(String title, String limit) {
        super(title);
        this.limit = limit;
    }

    /**
     * Adds a new Deadline task to the list of tasks.
     * @param title Title of task.
     * @param limit Deadline of task.
     * @return Task object created.
     */
    public static Task addDeadline(String title, String limit) {
        Task deadline = new Deadline(title, limit);
        taskList.add(deadline);
        return deadline;
    }

    @Override
    public String toString() {
        String time = String.format(" (by: %s)", limit);
        return "[D]" + super.toString() + time;
    }

    @Override
    public String toFileString() {
        return "D" + super.toFileString() + " | " + limit;
    }
}
