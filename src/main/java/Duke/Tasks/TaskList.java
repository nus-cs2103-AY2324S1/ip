package Duke.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Stores the list of items that the user has.
 */
public class TaskList {
    private ArrayList<Optional<Task>> list = new ArrayList<>();

    public TaskList(ArrayList<Optional<Task>> tasks) {
        this.list = tasks;
    }

    /**
     * Adds an item to the list.
     *
     * @param task The user's task
     */
    public void addToList(Task task) {
        list.add(Optional.of(task));

    }

    /**
     * Removes an item from the list.
     * Indexes start from 1, not zero
     *
     * @param index the index of the task to remove.
     * @return the task that was removed
     */
    public Optional<Task> removeFromList(int index) {
        Optional<Task> task = list.get(index - 1);
        list.remove(index - 1);
        return task;
    }

    /**
     * Marks a specific task in the list as done.
     * Indexes start from 1, not 0
     *
     * @param index
     * @return the task that was modified
     */
    public Optional<Task> markAsDone(int index) {
        Optional<Task> task = list.get(index - 1);
        task.ifPresent((t) -> t.setDone());

        return task;
    }

    /**
     * Marks a specific task in the list as undone.
     * Indexes start from 1, not 0
     *
     * @param index
     * @return the task that was modified
     */
    public Optional<Task> markAsUnDone(int index) {
        Optional<Task> task = list.get(index - 1);
        task.ifPresent((t) -> t.setUnDone());
        return task;
    }

    /**
     * Gets the number of non-null tasks in the list.
     *
     * @return integer representing the size
     */
    public int getSize() {
        return this.list.stream().filter(Optional::isPresent).collect(Collectors.toList()).size();
    }

    /**
     * Encodes the current Duke. Tasks in a string, each task separated by a newline.
     * @return String representation
     */
    public String serialize() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            Optional<Task> task = this.list.get(i);
            task.ifPresent((t) -> stringBuilder.append(t.encodeTask()));
            stringBuilder.append("\n");
        }


        return stringBuilder.toString();
    }

    public ArrayList<Optional<Task>> findTasksByName(String searchString) {
        List<Optional<Task>> filtered = this.list.stream().map(Optional::get)
                .map(a -> a.getName().contains(searchString) ? a : null)
                .map(Optional::ofNullable)
                .collect(Collectors.toList());

        return new ArrayList<>(filtered);

    }
    @Override
    public String toString() {
        StringBuilder resultMsg = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            Optional<Task> task = list.get(i);
            int finalI = i;
            task.ifPresent((t) -> {
                resultMsg.append(finalI + 1).append(". ").append(t.toString());

                resultMsg.append("\n");
            });

        }

        return resultMsg.toString();
    }

}
