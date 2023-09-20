package bongo;

import java.io.FileNotFoundException;

import bongo.command.Command;
import bongo.helper.BongoException;
import bongo.helper.Parser;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;
import javafx.scene.image.Image;

/**
 * The Bongo class, the class for the BongoBot.
 */
public class Bongo {
    private static final String FILEPATH = "data/bongo.txt";
    private Image user = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bongo = new Image(this.getClass().getResourceAsStream("/images/bongo.jpeg"));
    private final Storage storage;
    private final Ui ui;
    private TaskList tasks;

    /**
     * Initializes Bongo object with filepath.
     *
     * @param filepath Filepath of text file.
     */
    public Bongo(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            storage.checkIfFilesExist();
            this.tasks = new TaskList();
        } catch (BongoException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * Empty constructor for Bongo.
     */
    public Bongo() {
        this.ui = new Ui();
        this.storage = new Storage(FILEPATH);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            storage.checkIfFilesExist();
            this.tasks = new TaskList();
        } catch (BongoException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(this.tasks, this.ui, this.storage);
        } catch (BongoException e) {
            response = e.getMessage();
        }
        assert response != null : "A response should be printed to the user.";
        return response;
    }

    /**
     * Runs the Bongo bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (BongoException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        Bongo bongo = new Bongo(FILEPATH);
        bongo.run();
    }
}

