package dot;

import dot.errors.DotException;
import dot.storage.Storage;
import dot.tasks.TaskList;

/**
 * The Dot class, which integrates the high-level components together,
 * to form a robust, helpful and cheerful to-do list chatbot.
 */
public class Dot {
    private final Storage storage;
    private TaskList dotTaskList;

    /**
     * Constructor for Dot.
     *
     * @param maxSize The maximum number of tasks Dot can handle
     */
    public Dot(int maxSize) {
        assert maxSize >= 0 : "maxSize is supposed to be positive";
        String storageLocation = "./data/dot.txt";
        storage = new Storage(storageLocation);
        try {
            dotTaskList = TaskList.getTaskListFromArrayList(maxSize,
                    storage.getTasks(), storage);
        } catch (DotException e) {
            // GUI does not suppose calling e.handleError here
            dotTaskList = TaskList.getNewTaskList(100, storage);
        }
    }

    public TaskList getDotTaskList() {
        return this.dotTaskList;
    }

}
