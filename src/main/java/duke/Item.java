package duke;
/**
 * Encapsulates an item in the list
 */
public abstract class Item {
    private String name;
    private boolean isCompleted;

    public Item(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Mark this item as completed.
     */
    public void markCompleted() {
        this.isCompleted = true;
    }

    /**
     * Removes the "completed" mark on this item.
     */
    public void unmarkCompleted() {
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String rtnVal = "";
        if (this.isCompleted) {
            rtnVal += "[X] ";
        } else {
            rtnVal += "[ ] ";
        }
        return rtnVal + name;
    }
}
