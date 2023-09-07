package pogo;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pogo.commands.Command;
import pogo.commands.CommandResult;
import pogo.common.Messages;
import pogo.parsers.Parser;
import pogo.storage.Storage;
import pogo.storage.TextStorage;
import pogo.tasks.Task;
import pogo.ui.controllers.MainWindow;

/**
 * Runs the Pogo application.
 */
public class Pogo extends Application {
    /**
     * List of tasks in Pogo.
     */
    private List<Task> tasks;

    /**
     * Storage object to save and load tasks.
     */
    private Storage storage;
    private FXMLLoader fxmlLoader;


    /**
     * {@inheritDoc}
     */
    @Override
    public void init() throws Exception {
        storage = TextStorage.of();
        tasks = storage.load();
    }

    /**
     * Checks if the command result is an exit command.
     *
     * @param result the command result.
     * @return true if the command result is an exit command.
     */
    public static boolean isExit(CommandResult result) {
        return result.getFeedbackToUser().equals(Messages.EXIT_MESSAGE);
    }


    /**
     * Handles the user input
     *
     * @param input the user input.
     * @return the result of the command.
     */
    public CommandResult handleInput(String input) {
        Command command = Parser.parseCommand(input);
        command.setData(tasks);
        CommandResult result = command.execute();
        if (isExit(result)) {
            stop();
        }

        return result;
    }

    @Override
    public void start(Stage stage) {
        try {
            fxmlLoader = new FXMLLoader(Pogo.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            fxmlLoader.<MainWindow>getController().setPogo(this);
            fxmlLoader.<MainWindow>getController().showToUser(
                    Messages.STARTUP_MESSAGE
            );

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        fxmlLoader.<MainWindow>getController().showToUser(
                Messages.EXIT_MESSAGE
        );
        storage.save(tasks);
    }
}
