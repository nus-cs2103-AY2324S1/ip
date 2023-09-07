package duke.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);

    }

    public String addedMessage(){
        String ret = "";
        ret += "Got it. I've added this task:\n";
        ret += "  " + this + "\n";
        ret += "Now you have " + super.size + " tasks in the list.\n";

        return ret;
    }


    @Override
    public String toString() {

        return "[T]" + super.toString() ;
    }
}