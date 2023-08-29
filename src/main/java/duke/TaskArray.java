package duke;

import duke.task.Task;
import java.util.ArrayList;

public interface TaskArray {
    boolean isEmpty();
    int size();
    Task get(int i);
    void add(Task t);
    void remove(int i);
    ArrayList<String> toStringInFile();
}
