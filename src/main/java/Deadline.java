public class Deadline extends Task{

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return isDone ? "[D][X] "+this.description + " (by: "+this.date+")"
                : "[D][ ] "+this.description + " (by: "+this.date+")";
    }
}
