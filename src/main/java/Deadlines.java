public class Deadlines extends  Task{
    String by;
    public Deadlines(String text, String by){
        super(text);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()  + " (by: " + by + ")";
    }
}
