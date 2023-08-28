public class Deadline extends Task {
    private String date;

    public Deadline(String task, String date) throws DukeException{
        super(task);
        if (date.isEmpty() || date.equals(" ")) {
            throw new DukeException("What is your DEADLINE???\n");
        }
        this.date = date;
    }

    public Deadline(String task, String date, boolean finish) throws DukeException{
        super(task, finish);
        if (date.isEmpty() || date.equals(" ")) {
            throw new DukeException("What is your DEADLINE???\n");
        }
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " + this.date;
    }
}
