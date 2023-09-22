package duke.command;

import duke.TaskList;
import duke.Ui;

/** Abstraction of a command to print out a list. */
public class PrintList extends Command {

    private Ui ui = new Ui();
    private TaskList list;

    /**
     * Creates a command that can print out list.
     *
     * @param list List to be printed out.
     */
    public PrintList(TaskList list) {
        this.list = list;
    }

    @Override
    public String execute() {
        return this.list.printList();
    }
}
