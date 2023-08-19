public class DeadlineTask extends Task{
    String date;
    
    public DeadlineTask(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
