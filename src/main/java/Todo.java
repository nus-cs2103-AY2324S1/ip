public class Todo extends Task {
    String task;
    Boolean done;

    public Todo(String task) {
        super(task);
    }

    public String toString() {
        return "[T]" + super.toString();
    }

    public void print() {
        System.out.println(Duke.horizontalLine + "Got it. I've added this task:\n " + this.toString()+ "\n"
        + "Now you have " + Task.getCounter() + " tasks in the list\n" + Duke.horizontalLine);
    }
}
