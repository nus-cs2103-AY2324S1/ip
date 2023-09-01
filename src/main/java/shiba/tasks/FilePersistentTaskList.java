package shiba.tasks;

import java.util.ArrayList;
import java.util.List;

import shiba.exceptions.ShibaException;
import shiba.filehandler.Storage;
import shiba.ui.Replier;

/**
 * Implementation of a persistent task list that saves tasks to a file.
 */
public class FilePersistentTaskList implements PersistentTaskList {
    private final ArrayList<ShibaTask> tasks = new ArrayList<>();
    private final Storage storage;

    /**
     * Constructs a FilePersistentTaskList object.
     *
     * @param dataPath Path to the file where tasks are saved.
     */
    public FilePersistentTaskList(String dataPath) {
        storage = new Storage(dataPath);

        try {
            tasks.addAll(storage.readSavedTasks());
        } catch (ShibaException e) {
            Replier.printException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addTask(ShibaTask task) {
        tasks.add(task);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShibaTask removeIndex(int index) {
        return tasks.remove(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShibaTask get(int index) {
        return tasks.get(index);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return tasks.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save() throws ShibaException {
        storage.saveTasks(tasks);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ShibaTask> findTasksWithKeyword(String keyword) {
        ArrayList<ShibaTask> matchingTasks = new ArrayList<>();
        for (ShibaTask task : tasks) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
