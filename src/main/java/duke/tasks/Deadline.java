package duke.tasks;

public class Deadline extends Task {
    private String deadline;

    /**
    * constructs the deadline class
    *
    * @param task the description of the task
    * @param deadline the deadline of the task
    */
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }
    
    /**
    * returns the status of the deadline task
    *
    * @return a formatted string of the status of the deadline
    */
    @Override
    public String getStatus() {
        String time = "(by: " + deadline + ")";
        return "[DDL]" + super.getStatus() + " " + time;
    }

    /**
    * returns the deadline of the deadline task
    *
    * @return the deadline
    */
    @Override
    public String getTime() {
        return deadline;
    }

    @Override
    public String toFile() {
        return super.isDone ? ("D | 1 | " + super.task + " | " + this.deadline)
                            : ("D | 0 | " + super.task + " | " + this.deadline);
    }
}