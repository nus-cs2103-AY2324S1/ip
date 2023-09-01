package Tasks;

public class Task {
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

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Task)) {
            return false;
        }

        Task otherListItem = (Task) o;
        return otherListItem.itemName.equals(this.itemName);
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Marks the item as done.
     */
    public boolean setDone() {
        this.isDone = true;
        return true;
//        System.out.println("Nice! I've marked this task as done: \n    " + this.toString());
    }

    /**
     * Marks the item as not done.
     */
    public boolean setUnDone() {
        this.isDone = false;
        return false;
//        System.out.println("Nice! I've marked this task as undone: \n    " + this.toString());
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


    public void printActionSuccess(Commands command) {
        switch(command) {
            case TODO: {

            }
        }
    }


    /**
     * Prints the task, formatted
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.itemName;
    }

}
