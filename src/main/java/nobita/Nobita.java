package nobita;

import nobita.command.Command;
import nobita.exception.NobitaException;
import nobita.parser.Parser;
import nobita.storage.Storage;
import nobita.task.TaskList;
import nobita.ui.Ui;

/**
 *  Class that encapsulates a robot application name Nobita.
 *
 *  @author Zheng Chenglong
 */
public class Nobita {

    /** The Ui that interact with the user */
    private final Ui ui;

    /** The list of tasks stored */
    private final TaskList tasks;

    /** The Storage used for reading and writing data to local file */
    private final Storage storage;

    /**
     * Constructs Nobita.
     */
    public Nobita() {
        this.ui = new Ui();
        this.storage = new Storage(System.getProperty("user.dir"));
        this.tasks = storage.readFile();
    }

    /**
     * Starts Nobita robot.
     *
     * @param args an array of command-line arguments for the application.
     */
    public static void main(String[] args) {
        new Nobita().run();
    }

    /**
     * Start running Nobita robot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                System.out.println(fullCommand);
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (NobitaException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
}
