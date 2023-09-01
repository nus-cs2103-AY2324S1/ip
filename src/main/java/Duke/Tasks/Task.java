package Duke.Tasks;

public abstract class Task {
    private String itemName;
    private boolean isDone;

    public Task(String itemName) {
        this.itemName = itemName;
    }

    @Override
    public boolean equals(Object o) {
        // If the object is compared with itself then return true
        if (o == this) {
            return true;
        }

        /* Check if o is an instance of Task or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Task)) {
            return false;
        }

        Task otherListItem = (Task) o;
        return otherListItem.itemName.equals(this.itemName) && otherListItem.isDone == this.isDone;
    }

    /**
     * Marks the item as done.
     */
    public boolean setDone() {
        this.isDone = true;
        return true;
    }

    /**
     * Marks the item as not done.
     */
    public boolean setUnDone() {
        this.isDone = false;
        return false;
    }


    /**
     * Gets an icon to represent the state of the task.
     *
     * @return a stringified icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Gets the task name.
     *
     * @return the task name
     */
    public String getName() {
        return this.itemName;
    }


    /**
     * Gets the task type as a single character.
     * T = Todo
     * D = Deadline
     * E = Event
     *
     * @return encoded character
     */
    public abstract String getTaskType();

    /**
     * Encodes the task into a formatted string to be stored in a database
     *
     */
    public String encodeTask() {
        return this.getTaskType() + " | " + (this.isDone ? "1" : "0") + " | " + this.itemName;
    }

    /**
     * Prints the task, formatted
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.itemName;
    }

}
