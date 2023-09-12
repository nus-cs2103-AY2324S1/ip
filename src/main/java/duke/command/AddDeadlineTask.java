package duke.command;

import java.time.LocalDate;

import duke.Deadline;
import duke.Task;
import duke.TaskList;
import duke.parser.DateParser;


/** Abstraction of a command to add a deadline task to a list. */
public class AddDeadlineTask extends AddTask {

    /**
     * Creates a command to a deadline task to a list.
     *
     * @param list List that deadline task to be added.
     * @param specifications Description of the deadline task.
     */
    public AddDeadlineTask(TaskList list, String specifications) {
        super(list, specifications);
    }

    @Override
    public String execute() {
        try {
            String[] splits = this.specifications.split("/by", 2);
            String description = splits[0];
            LocalDate date = DateParser.parseDate(splits[1]);
            Task deadlineTask = new Deadline(description, false, date);
            this.list.store(deadlineTask);
            return this.ui.showTaskAdded(deadlineTask, this.list.length());
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new IllegalArgumentException(
                    "OOPS!!! The description of a deadline must have <task> /by <time>.");
        }
    }
}
