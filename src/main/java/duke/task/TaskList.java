package duke.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

import duke.DukeException;

/**
 * Represents a task list.
 */
public class TaskList extends ArrayList<Task> {

    public String getTasksMatchingQuery(String query) {
        StringBuilder stringBuilder = new StringBuilder();

        IntStream.range(0, this.size())
                .filter(i -> this.get(i).getDescription().contains(query))
                .mapToObj(i -> String.format("%d. %s\n", i + 1, this.get(i)))
                .forEach(stringBuilder::append);

        String filteredList = stringBuilder.toString();
        if (filteredList.length() == 0) {
            return "No Items in List";
        } else {
            return filteredList;
        }
    }

    public boolean taskExists(Task query) {
        return this.stream().anyMatch(t -> t.equals(query));
    }

    /**
     * Add a new Task to the TaskList.
     * First Checks if the task exists in the current list, if it does, throws a DukeException
     *
     * @param element task to be added
     * @throws DukeException if task already exists
     */
    public void addTask(Task element) throws DukeException {
        if (taskExists(element)) {
            throw new DukeException("Sorry this task already exists");
        }
        super.add(element);
    }
}
