
public class Task extends Corubi {
    private boolean isDone;
    private final String NAME;
    final String DONE = "[X] ";
    final String NOTDONE = "[] ";


    Task(String name, boolean isDone) {
        this.NAME = name;
        this.isDone = isDone;
    }

    /*
    Method name: checkDone
    Description: Checks if the task is done
     */
    public boolean checkDone() {
        return isDone;
    }

    /*
    Method name: toString
    Description: Prints the task name and whether it is done
     */
    public String toString() {
        return this.checkDone()
                ? DONE + this.NAME
                : NOTDONE + this.NAME;
    }

    /*
    Method name: markDone
    Description: Checks the specified task
     */
    public void markDone() {
        isDone = true;
    }

    /*
    Method name: unmarkDone
    Description: Unchecks the specified task
     */
    public void unmarkDone() {
        isDone = false;
    }
}
