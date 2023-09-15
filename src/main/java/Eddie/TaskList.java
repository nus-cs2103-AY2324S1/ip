package Eddie;

import Eddie.Tasks.Task;

import java.util.ArrayList;

/**
 * Represents the tasklist which holds all the tasks.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void add(Task t) {
        tasks.add(t);
    }

    public static void delete(int index) {
        tasks.remove(index);
    }

    public static Task get(int index) {
        return tasks.get(index);
    }

    public static void clear() {
        tasks.clear();
    }

    public static int size() {
        return tasks.size();
    }


}
