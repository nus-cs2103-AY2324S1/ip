package duke;

import java.util.ArrayList;
import java.util.stream.Collectors;
/**
 * FindTask class with a field keyword.
 */
public class FindTask extends Command {
    private String keyword;

    /**
     * Constructor for FindTask object
     * @param keyword String which we are looking for in the task
     */
    public FindTask(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList lst, Ui ui, Storage storage) throws InvalidInputException {
        ArrayList<Task> found = new ArrayList<>(
                lst.stream()
                        .filter(task -> task.getName().contains(this.keyword))
                        .collect(Collectors.toList())
        );
        if (found.size() == 0) {
            throw new InvalidInputException("No matching tasks found!");
        }
        assert found.size() != 0 : "found should not be empty!";
        TaskList foundList = new TaskList(found);
        return ui.foundMessage(foundList);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
