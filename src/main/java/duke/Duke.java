package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The Duke Program is a chatbot that takes in
 * input from the user and responds accordingly
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private UI ui;
    private String uiOutput;
    private boolean justCreatedFile = false;

    /**
     * Constructor for the Duke object.
     * @param filePath the location of the storage file.
     */
    public Duke(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            this.justCreatedFile = true;
            try {
                File f = new File(filePath);
                if (f.createNewFile()) {
                    tasks = new TaskList();
                }
            } catch (IOException Ioe) {
                ui.showIoError();
            }
        }
    }

    public String getResponse(String text) {
        try {
            Command c = Parser.parse(text);
            uiOutput = c.execute(tasks, ui, storage);
        } catch (DukeException e) {
            uiOutput = ui.showError(e.getMessage());
        } catch (IOException e) {
            uiOutput = ui.showIoError();
        } catch (IndexOutOfBoundsException e) {
            uiOutput = ui.showOutOfBounds();
        } catch (DateTimeParseException e) {
            uiOutput = ui.showTimeFormatError();
        }
        return uiOutput;
    }

    public boolean getJustCreatedFile() {
        return this.justCreatedFile;
    }

}
