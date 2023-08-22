public class ListItem {
    private String itemName;
    private boolean isDone;

    public ListItem(String itemName) {
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
        if (!(o instanceof ListItem)) {
            return false;
        }

        ListItem otherListItem = (ListItem) o;
        return otherListItem.itemName.equals(this.itemName);
    }

    public String getItemName() {
        return itemName;
    }

    /**
     * Marks the item as done.
     */
    public void setDone() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done: \n" + this.toString());
    }

    /**
     * Marks the item as not done.
     */
    public void setUnDone() {
        this.isDone = false;
    }

    /**
     * Prints the task, formatted
     */
    @Override
    public String toString() {
        return "[" + (this.isDone ? "X" : " ") + "] " + this.itemName;
    }
}
