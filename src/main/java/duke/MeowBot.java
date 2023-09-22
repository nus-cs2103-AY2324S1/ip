package duke;
import java.io.IOException;

import duke.command.Command;
import duke.helper.Parser;
import duke.helper.Storage;
import duke.helper.Ui;
import duke.task.DukeException;
import duke.task.TaskList;
import javafx.scene.image.Image;


/**
 * MeowBot is the main class which runs the iP project in its main function
 * @since 2023-09-22
 */


public class MeowBot {
    private final Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private String fileName;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Represents the 2 possible inputs, that is either User Dialog or response from MeowBot
     */

    public enum Dialog {
        USER,
        MEOWBOT

    }
    /**
     * @param file indicates the location of the file where data is stored
     * @throws DukeException when generating the tasks back from the data file
     * @throws IOException when the data file cannot be found
     */
    public MeowBot(String file) throws DukeException, IOException {
        this.ui = new Ui();
        this.fileName = file;
        this.storage = new Storage(this.fileName);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            System.out.println("Meow???? I cant find your data");
            storage.createNewFile();
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            throw new DukeException("Meow???? I cannot write to your files :(");
        }
    }

    /**
     * Empty default constructor for MeowBot
     */

    public MeowBot() {
        this.ui = new Ui();
        this.fileName = "stop";
    }

    /**
     * Main method to run the MeowBot that will execute commands given
     */

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
        ui.bye();
    }

    /**
     * Processes the user's input and attempts to execute the command given
     * @param input The string parsed into the user
     * @return response returns the String format of the result of the execution
     */
    public String getResponse(String input) {
        String response = "Invalid";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            response = e.toString();
        }
        return response;
    }

    public static void main(String[] args) throws DukeException, IOException {
        new MeowBot("src/main/data/meowbot.txt").run();
    }

}
