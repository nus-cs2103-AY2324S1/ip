package Eddie.Tasks;

/**
 * Represents a task which can be created and listed.
 */
public class Task {
    private boolean done;
    private String name;

    public Task(){};
    public Task(String name) {
        this.done = false;
        this.name = name;

    }

    public String getStatus(){
        return done ? "x" : " ";
    }

    public void taskIsDone() {
        this.done = true;
    }
    public void taskNotDone() {
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return "Eddie.Tasks.Task";
    }

    public String toString() {
        return "[" + this.getType() + "]" + "[" + this.getStatus() + "]" + this.getName();
    }

    public String getDeadline() {return null;}

    public String getStartDate() {return null;}

    public String getEndDate() {return null;}

}
