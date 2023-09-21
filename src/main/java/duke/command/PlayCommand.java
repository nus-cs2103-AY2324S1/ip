package duke.command;

import duke.exception.InvalidParametersException;
import duke.main.Music;
import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

public class PlayCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidParametersException {
        Music.playMusic();
        output.delete(0, output.length());
        output.append(":D");
    }
}
