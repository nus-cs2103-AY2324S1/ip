public class Deadline extends Task{
    protected String date;
    public Deadline(String description, String date) {
        super(description, "D");
        this.date = date;
        this.addedTaskDescription();
    }
    @Override
    public String getDetails(){
        return String.format(" (by: %s)", date);
    }
}
