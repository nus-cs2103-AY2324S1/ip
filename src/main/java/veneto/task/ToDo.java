package veneto.task;

public class ToDo extends Task {
    /* Constructors */
    /**
     * create a new ToDo task
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * create a new ToDo task from storage file
     * @param des the description of the task
     * @param mark if the task is marked
     */
    public ToDo(String des, int mark) {
        super(des, mark != 0);
    }

    /* Methods */
    /**
     * explanation of the task
     * @return return task details
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * generate texts for storage
     * @return String of the task data
     */
    @Override
    public String saveToString() {
        return "toDo," + super.saveToString();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
