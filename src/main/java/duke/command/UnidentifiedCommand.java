package duke.command;

import duke.exception.UnidentifiedCommandException;
import duke.io.FileIO;
import duke.io.Printer;
import duke.task.TaskList;

/** Represents a command which Duke does not recognise. Will throw exception during action. */
public class UnidentifiedCommand extends Command {

    /**
     * Returns an UnidentifiedCommand
     *
     * @param out Printer to print output
     * @param taskList Tasklist to update
     * @param savefile File to write tasks to
     * @return an UnidentifiedCommandException
     */
    public UnidentifiedCommand(Printer out, TaskList taskList, FileIO savefile) {
        super(out, taskList, savefile);
    }

    /**
     * The unidentified command action
     *
     * @throws UnidentifiedCommandException always
     */
    @Override
    public void action() {
        throw new UnidentifiedCommandException();
    }
}
