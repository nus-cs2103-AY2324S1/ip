package duke.command;

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
        ui.sendMessage("Bye. Happy to be able to serve you!!!\nShutting down...");
        closeChatbot();
    }

    /**
     * Closes the chatbot after three seconds of pause.
     */
    public void closeChatbot() {
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
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
}
