public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String deadline) throws DukeException{
        super(task);
        this.deadline = deadline;

        // if (task.isBlank() || task.isEmpty()) {
        //     throw new DukeException("The description of a Deadline task cannot be empty.");
        // }

        // if (deadline.isBlank() || deadline.isEmpty()) {
        //     throw new DukeException("The deadline of a Deadline task cannot be empty.");
        // }
    }

    @Override
    public String toFileString() {
        if (super.isDone) {
            return "D | 1 | " + super.task + " | " + this.deadline;
        } 
        return "D | 0 | " + super.task + " | " + this.deadline;
    }

    @Override
    public String getStatus(){
        String time = "(by: " + deadline + ")";
        return "[Deadline]" + super.getStatus() + " " + time;
    }
}