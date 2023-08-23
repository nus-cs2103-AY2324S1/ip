public class Event extends Task{

    protected String start;
    protected String end;

    public Event(String list, String start, String end, TaskType type) {
        super(list, type);
        this.start = start;
        this.end = end;
    }

    /**
     * Marks the task as completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful marking of the task.
     * @throws DukeException If the task has already been marked as done.
     */
    @Override
    public String setMarked() throws DukeException{
        super.setMarked();
        return "Nice! I've marked this task as done:\n" + toString();
    }

    /**
     * Marks the task as not completed and returns a message indicating the task's new status.
     *
     * @return A message indicating the successful unmarking of the task.
     * @throws DukeException If the task has already been marked as not done.
     */
    @Override
    public String setUnmarked() throws DukeException{
        super.setUnmarked();
        return "OK, I've marked this task as not done yet:\n" + toString();
    }

    @Override
    public String toString() {
        return super.toString() + " ( from: " + this.start + " to: " + this.end +")";
    }

}
