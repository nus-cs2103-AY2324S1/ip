package Duke.tasks;


public class Todo extends Task {


    public Todo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this);
        System.out.println("Now you have " + super.size + " tasks in the list.");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString() ;
    }
}