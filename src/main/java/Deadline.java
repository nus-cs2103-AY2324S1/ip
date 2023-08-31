public class Deadline extends Task {
    protected String date;

    public Deadline(String description, String date){
        super(description);
        this.date = date;
    }

    @Override
    public String store(){
        return String.format("D | %s | %s | %s", this.isDone, this.description, this.date);
    }
    @Override
    public String toString(){
        return String.format("[D] %s (by: %s)", super.toString(), this.date);
    }

}
