package smolbrain;

import smolbrain.command.Command;
import smolbrain.exception.*;
import smolbrain.task.TaskList;

/**
 * Smolbrain class which is the chatbot. It takes in input to create and save tasks.
 */
public class Smolbrain {

    public static boolean loading = true;

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a smolbrain object.
     *
     * @param filePath Filepath of the save file.
     */
    public Smolbrain(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage.load());
    }

    /**
     * Runs the smolbrain chatbot.
     */
    public void run() {

        ui.showWelcome();
        boolean isExit = false;

        while(!isExit) {
            try {
                String input = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (InvalidRangeException | MissingDescriptionException | MissingTimeException |
                     InvalidNumberException | InvalidDateTimeException | MissingKeywordException e) {
                ui.showError(e);
            }
            ui.showLine();
        }
    }

    /**
     * Creates a new Smolbrain chatbot object with specified save file path and runs the chatbot.
     *
     * @param args User inputs for commands.
     */
    public static void main(String[] args) {
        new Smolbrain("data.txt").run();
    }
}
