package duke.task.todo;

import duke.task.Task;

/**
 * ToDo class is a task that contains a name only without any dates
 */
public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }
    /**
     * This method gives the string representation of a ToDo task when it is in a list
     *
     * @return The String representation of a ToDo Task
     */
    @Override
    public String showTaskinList() {
        return "[T]" + super.showTaskinList();
    }

    /**
     * This method gives the string representation of a ToDo task when it is in the saved list
     *
     * @return The String representation of a ToDo task in the saved list
     */
    @Override
    public String printList() {
        return "T | " + super.printList();
    }


}
