public class Events extends Task {
    private String from;
    private String to;
    Events(String name, String from, String to, boolean isDone) {
        super(name, isDone);
        this.from = from;
        this.to = to;
    }

    /*
    Method name: toString
    Description: Prints the task name and whether it is done
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + "(from : " + this.from + " to: " + this.to + ")";
    }

    /*
Method name: markDone
Description: Checks the specified task
 */
    @Override
    public void markDone() {
        super.markDone();
        System.out.println("Oki, I've marked this task as done: \n" + this.toString());
    }

    /*
    Method name: unmarkDone
    Description: Unchecks the specified task
     */
    @Override
    public void unmarkDone() {
        super.unmarkDone();
        System.out.println("Damn bro...unmarked this task :( : \n" + this.toString());
    }
}
