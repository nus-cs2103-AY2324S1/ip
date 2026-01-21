package veneto.task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    /* Fields */
    /**
     * indicates if the storage is changed after a command operates
     * will be modified when command operates.
     */
    public int storageChanged = 0;

    /* Constructors */
    /**
     * creates a new TaskList
     */
    public TaskList() {}

    /**
     * creates a new TaskList
     * @param initialCapacity the initial capacity of the list
     */
    public TaskList(int initialCapacity) {
        super(initialCapacity);
    }

    /* Methods */
    /**
     * the string representation of the list
     * @return all tasks in the list
     */
    @Override
    public String toString() {
        StringBuffer rt = new StringBuffer();
        for (int i = 0; i < this.size(); i++) {
            rt.append("  " + (i+1) + ". " + this.get(i).toString() + "\n");
        }
        return rt.toString();
    }
}
