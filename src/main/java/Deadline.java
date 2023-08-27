public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String convertIsDone() {
        return super.isDone ? "1" : "0";
    }
    @Override
    public String saveToFileString(){
        return "T | " +  convertIsDone() + " | " + description + " | " + this.by+"\n";
    }
}
