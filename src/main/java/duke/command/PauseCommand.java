package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Music;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to Pause music */
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
        OUTPUT.delete(0, OUTPUT.length());
        OUTPUT.append(":o");
    }
}
