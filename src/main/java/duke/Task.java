package duke;
public class Task {
    // task object has 2 attributes, name of the task and whether it is marked
    protected String name;
    protected boolean isMarked;

    public Task(String name, boolean isMarked) {
        this.name = name;
        this.isMarked = isMarked;
    }

    //gets the name of the task
    public String getName() {
        return name;
    }

    //sets the name of the task

    public void setName(String name) {

        this.name = name;
    }

    //checks if the task is already marked
    public boolean isMarked() {
        return isMarked;
    }

    // sets the task as marked
    public void setMarked(boolean marked) {
        isMarked = marked;
    }

    @Override
    public String toString() {
        if (isMarked) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
