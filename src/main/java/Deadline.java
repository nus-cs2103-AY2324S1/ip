import exception.DukeException;

public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String deadline) throws DukeException{
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String getStatus(){
        String time = "(by: " + deadline + ")";
        return "[Deadline]" + super.getStatus() + " " + time;
    }

    @Override
    public String getTime() {
        return deadline;
    }

    @Override
    public String toFileString() {
        return super.isDone ? ("D | 1 | " + super.task + " | " + this.deadline)
        : ("D | 0 | " + super.task + " | " + this.deadline);
    }
}