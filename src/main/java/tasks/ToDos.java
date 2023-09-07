package tasks;

public class ToDos extends Task {
    public ToDos(String name, boolean isDone) {
        super(name, isDone);
    }

    /*
    Method name: toString
    Description: Prints the task name and whether it is done
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /*
    Method name: markDone
    Description: Checks the specified task
     */
    @Override
    public String markDone() {
        super.markDone();
        return "Oki, I've marked this task as done: \n" + this.toString();
    }

    /*
    Method name: unmarkDone
    Description: Unchecks the specified task
     */
    @Override
    public String unmarkDone() {
        super.unmarkDone();
        return "Damn bro...unmarked this task :( : \n" + this.toString();
    }
}
