package duke.tasks;

public class ToDo extends Task {

    /**
    * construct the todo class
    *
    * @param task the description of the todo
    */
    public ToDo(String task) {
        super(task);
    }

    /**
    * returns the status of the todo task
    *
    * @return a formatted string of the status of the todo
    */
    @Override
    public String getStatus() {
        return "[To-Do]" + super.getStatus();
    }

    @Override
    public String toFile() {
        return super.isDone ? ("T | 1 | " + super.task) : ("T | 0 | " + super.task);
    }
}