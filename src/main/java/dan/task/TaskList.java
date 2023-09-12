package dan.task;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    /** Fields */
    public int storageChanged = 0;

    /** Constructors */
    public TaskList() {}
    public TaskList(int initialCapacity) {
        super(initialCapacity);
    }

    /** Methods */
    @Override
    public String toString() {
        StringBuffer rt = new StringBuffer();
        for (int i = 0; i < this.size(); i++) {
            rt.append("  " + (i+1) + ". " + this.get(i).toString() + "\n");
        }
        return rt.toString();
    }

}
