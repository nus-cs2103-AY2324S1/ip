package duke.command;

import java.io.IOException;

import duke.DukeList;
import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeException;
import duke.task.Task;

/**
 * The TagCommand class represents a command to tag a task.
 */
public class TagCommand extends Command {

    /**
     * Constructs a new Command object with the specified command.
     *
     * @param command The input command string.
     */
    public TagCommand(String command) {
        super(command);
    }

    @Override
    public String execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        String[] index = this.command.split(" ", 2);
        int key;
        String tag;
        try {
            String[] details = index[1].split("#", 2);
            key = Integer.parseInt(details[0].strip());
            tag = details[1];
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid key given");
        }
        if (key > tasks.getSize() || key < 1) {
            throw new DukeException("Key exceeds size of list");
        }
        Task task = tasks.get(key - 1);
        task.setTag(tag);
        try {
            storage.writeToFile(tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to write to file");
        }
        return ui.tag(task);
    }
}
