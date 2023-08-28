public class Task extends Corubi {
    private boolean isDone;
    private String name;
    final String done = "[X] ";
    final String notDone = "[] ";


    Task(String name, boolean isDone) {
        this.name = name;
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
                ? done + this.name
                : notDone + this.name;
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
