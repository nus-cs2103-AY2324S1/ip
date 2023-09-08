package duke.task;

import java.util.ArrayList;
import java.util.stream.IntStream;

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
}
