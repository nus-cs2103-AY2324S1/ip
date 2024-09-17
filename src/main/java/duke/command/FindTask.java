package duke.command;

import duke.TaskList;

/** Abstraction of command to list out task matching user desired search input. */
public class FindTask extends Command {

    private TaskList list;
    private String specifications;

    /**
     * Creates a FindTask command to find task matching user input.
     *
     * @param list List containing task to be filtered.
     * @param specifications Input that task should match with.
     */
    public FindTask(TaskList list, String specifications) {
        this.list = list;
        this.specifications = specifications;
    }

    @Override
    public String execute() {
        if (specifications.isEmpty()) {
            throw new IllegalArgumentException("Please indicate word to find");
        }
        return this.list.find(specifications);
    }
}
