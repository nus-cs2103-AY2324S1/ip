public class Deadline extends Task{
    private String deadline;
    Deadline(String s){
        super(s.split("/by",0)[0].trim());
        this.deadline = s.split("/by",0)[1].trim();
    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline +")";
    }
}
