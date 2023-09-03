package duke.command;

import duke.exception.DukeException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;

/** Represents a find command. Finds tasks based on the input search string. */
public class FindCommand extends Command {
    private String search;

    /**
     * Returns a FindCommand
     *
     * @param out Printer to print output to
     * @param tasklist Tasklist to read
     * @param file File to save tasks to
     * @param search searches task description
     * @return a FindCommand
     */
    public FindCommand(Printer out, TaskList taskList, FileIO file, String search) {
        super(out, taskList, file);
        this.search = search;
    }

    /**
     * The actions taken during the search command.
     *
     * @throws DukeException if search string is empty.
     */
    @Override
    public void action() {
        if (search.equals("")) {
            throw new DukeException("The argument of find cannot be empty");
        }

        out.print(taskList.filter(search));
    }
}
