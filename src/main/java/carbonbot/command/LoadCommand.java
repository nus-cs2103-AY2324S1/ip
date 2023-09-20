package carbonbot.command;

import carbonbot.Storage;
import carbonbot.TaskList;
import carbonbot.Ui;
import carbonbot.exception.CarbonDataFileException;
import carbonbot.exception.CarbonInputParseException;
import carbonbot.exception.CarbonStorageException;

/**
 * The command loads a data file from the disk.
 */
public class LoadCommand extends Command {
    private final String filePath;
    /**
     * Constructs an LoadCommand object to load the data file into task list.
     *
     * @param filePath Path to the data file.
     */
    public LoadCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws CarbonInputParseException, CarbonStorageException, CarbonDataFileException {
        if (this.filePath.isBlank()) {
            throw new CarbonInputParseException("The path cannot be empty. "
                    + "Example of valid usage: load ./data/my_task.txt");
        }

        // Replace storage with new filePath
        storage.setFilePath(filePath);
        tasks.setTaskList(storage.getTasks());
        ui.bufferMessage("The data file has been successfully loaded.\n"
                + "You currently have " + tasks.size() + " tasks.");
    }
}
