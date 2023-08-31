package Duke.duke;

import Duke.exception.DukeException;
import Duke.message.Message;
import Duke.parser.Ui;
import Duke.storage.Storage;
import Duke.application.Application;

/**
 * Represents the main class of the Duke application, responsible for initializing and running the application.
 */
public class Duke {
    @SuppressWarnings("FieldCanBeLocal")
    private final String name = "Iris";
    private Ui ui;

    private Application application;

    private boolean awake = true;

    /**
     * Constructs a Duke object and initializes the components.
     *
     * @param filePath The file path for data storage.
     */
    public Duke(String filePath) {
        try {
            ui = new Ui();
            Storage storage = new Storage(filePath);
            application = new Application(this, storage);
        } catch (DukeException e) {
            ui.ShowError(e);
        }
    }

    /**
     * Initiates the execution of the Duke application.
     */
    public void run() {
        ui.ShowMessage(Message.OnGreeting(name));
        while(awake){
            try {
                if(ui.HasNext())
                    ui.ShowMessage(ui.ParseLine().execute(application));
            } catch (DukeException de){
                ui.ShowError(de);
            }
        }
    }

    /**
     * Shuts down the program.
     */
    public void kill() {
        awake = false;
    }

    /**
     * Entry point for Duke.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\ortt2\\Documents\\ip\\src\\data\\tasks.txt").run();
    }
}
