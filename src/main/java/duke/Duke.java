package duke;

import java.io.IOException;
import java.time.DateTimeException;

import duke.Command.Command;
import duke.Exception.DukeException;


/**
 *  Main class of the chatbot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * The Duke constructor.
     */
    public Duke() {
        this.storage = new Storage("data/duke.txt");
        this.ui = new Ui();
        try {
            tasks = storage.saveTasks();
        } catch (IOException e) {
            tasks = new TaskList();
            e.printStackTrace();
        }
    }

    //  @@author inezkok - reused.
    //  modified the author's getResponse method and made use of their output methods
    //  (ie. resetting and appending strings to the output in the ui class) to display buddy's response on the gui.
    //  I viewed this person's method when referring to the gui forum thread for help when my gui would not
    //  show the duke's response although the functionalities worked.
    /**
     * Returns the Duke response to a given input.
     * @param input The user input that is passed in.
     * @return The Duke response.
     */
    public String getResponse(String input) {
        try {
            ui.resetOutput();
            Command c = Parser.addToList(input);
            c.execute(ui, storage, tasks);
            return ui.getOutput();
        } catch (DukeException e) {
            return ui.showError(e);
        } catch (IOException i) {
            return Parser.handleException(i);
        } catch (DateTimeException | NumberFormatException e) {
            return Parser.handleException(e);
        }
    }
    //  @@author
}
