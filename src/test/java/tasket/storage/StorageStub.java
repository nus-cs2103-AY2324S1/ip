package tasket.storage;

import java.util.ArrayList;

import tasket.data.TaskList;
import tasket.exception.TasketException;
import tasket.task.Deadline;
import tasket.task.Event;
import tasket.task.Task;
import tasket.task.ToDo;

/**
 * The class for storage stub.
 */
public class StorageStub implements StorageInterface {

    /**
     * {@inheritDoc}
     * Insert fake data for testing.
     *
     * @return A list of saved tasks.
     */
    @Override
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book"));
        tasks.add(new Deadline("return book", "Sat 2pm"));
        tasks.add(new Event("project meeting", "Sun 8pm", "10pm"));

        return tasks;
    }

    /**
     * Loads the task file and convert it to task list.
     * Different from load(), the tasks include tags.
     * @return A list of saved tasks.
     */
    public ArrayList<Task> loadWithTags() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("read book", new String[] {"study", "exam"}));
        tasks.add(new Deadline("return book", "Sat 2pm", new String[] {"library"}));
        tasks.add(new Event("project meeting", "Sun 8pm", "10pm", new String[] {"cs2103t", "tp"}));

        return tasks;
    }

    /**
     * {@inheritDoc}
     * This is done when adding a new task.
     *
     * @param taskSave The task to be saved in save format.
     * @throws TasketException If IO error occurs.
     */
    @Override
    public void append(String taskSave) {

    }

    /**
     * {@inheritDoc}
     * This is done whenever there is mark, unmark and delete operations.
     *
     * @param taskList The task list to get the tasks from.
     * @throws TasketException If IO error occurs.
     */
    @Override
    public void rewriteSaveFile(TaskList taskList) {

    }

}
