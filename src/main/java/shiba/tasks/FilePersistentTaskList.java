package shiba.tasks;

import shiba.exceptions.ShibaException;
import shiba.filehandler.Storage;
import shiba.ui.Replier;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a persistent task list that saves tasks to a file.
 */
public class FilePersistentTaskList implements PersistentTaskList {
    private final ArrayList<ShibaTask> tasks = new ArrayList<>();
    private final Storage storage;

    public FilePersistentTaskList(String dataPath) {
        storage = new Storage(dataPath);

        try {
            tasks.addAll(storage.readSavedTasks());
        } catch (ShibaException e) {
            Replier.printException(e);
        }
    }

    @Override
    public void addTask(ShibaTask task) {
        tasks.add(task);
    }

    @Override
    public ShibaTask removeIndex(int index) {
        return tasks.remove(index);
    }

    @Override
    public ShibaTask get(int index) {
        return tasks.get(index);
    }

    @Override
    public int size() {
        return tasks.size();
    }

    @Override
    public void save() throws ShibaException {
        storage.saveTasks(tasks);
    }

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
