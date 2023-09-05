package smolbrain;

import smolbrain.command.Command;
import smolbrain.exception.InvalidDateTimeException;
import smolbrain.exception.InvalidNumberException;
import smolbrain.exception.InvalidRangeException;
import smolbrain.exception.MissingDescriptionException;
import smolbrain.exception.MissingKeywordException;
import smolbrain.exception.MissingTimeException;
import smolbrain.task.TaskList;

/**
 * Smolbrain class which is the chatbot. It takes in input to create and save tasks.
 */
public class Smolbrain {

    private static boolean loading = true;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a smolbrain object.
     *
     * @param filePath Filepath of the save file.
     * @param mainwindow MainWindow object for this application.
     */
    public Smolbrain(String filePath, MainWindow mainwindow) {
        ui = new Ui(mainwindow);
        storage = new Storage(filePath, ui);
        tasks = new TaskList(storage.load(), ui);
    }

    /**
     * Processes the given input string to generate and execute commands.
     *
     * @param input Input string by user.
     */
    public void process(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (InvalidRangeException | MissingDescriptionException | MissingTimeException
                 | InvalidNumberException | InvalidDateTimeException | MissingKeywordException e) {
            ui.showError(e);
        }
    }

    /**
     * Runs the smolbrain chatbot.
     */
    public void run() {
        ui.showWelcome();
    }

}
