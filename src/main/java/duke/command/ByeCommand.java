package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.util.Duration;

/**
 * Displays a farewell message and indicates that the application should exit.
 */
public class ByeCommand implements Command {

    /**
     * Executes the command by displaying a farewell message to the user.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving task data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null && storage != null;
        ui.sendMessage("Bye-bye. Happy to be able to serve you!!!\nShutting down...");
        closeChatbot();
    }

    /**
     * Closes the chatbot after two seconds of pause.
     */
    public void closeChatbot() {
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> Platform.exit());
        pause.play();
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     */
    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    /**
     * Does nothing.
     *
     * @param tasks The list of tasks.
     * @return The command to be executed.
     * @throws DukeException If an error occurs during command execution.
     */
    @Override
    public Command undoTask(TaskList tasks) throws DukeException {
        return new EmptyCommand();
    }
}
