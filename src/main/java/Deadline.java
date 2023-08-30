import Exceptions.EmptyDescriptionException;
import Exceptions.IllegalFormatException;

public class Deadline extends Task{
    private String deadline;
    Deadline(String s) throws EmptyDescriptionException, IllegalFormatException {
        super(s.split("/by",0)[0].trim());
        if(!s.contains("/by")){
            throw new IllegalFormatException("Missing \"/by\" after task description");
        }
        this.deadline = s.split("/by",0)[1].trim();
    }
    Deadline(String desc,String deadline) throws EmptyDescriptionException {
        super(desc);
        this.deadline = deadline;

    }

    public String toString(){
        return "[D]" + super.toString() + " (by: " + deadline +")";
    }
    public String getDeadline(){ return deadline;}
}
