package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Music;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/** Command to Play music **/
public class PlayCommand implements Command {
    /**
     * Play music.
     *
     * @param taskList TaskList containing the list of tasks.
     * @param ui UI that interacts between the app and the user.
     * @param storage Storage to save or load data from file.
     * @throws InvalidParametersException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        Music.playMusic();
        OUTPUT.delete(0, OUTPUT.length());
        OUTPUT.append(":D");
    }
}
