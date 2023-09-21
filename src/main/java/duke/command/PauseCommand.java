package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Music;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class PauseCommand implements Command {
    /**
     * Pause music.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     * @throws InvalidParametersException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        Music.pauseMusic();
        output.delete(0, output.length());
        output.append(":o");
    }
}
