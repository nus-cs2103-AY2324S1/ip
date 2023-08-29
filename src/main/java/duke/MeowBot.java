package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.*;
import duke.helper.*;
import duke.command.*;
public class MeowBot {

    /**
     * field filename is the place where the data txt file is located
     * field storage is the class that handles with the storing and updating of the txt file
     * field tasks is the class that stores the tasks
     * field ui is the class that deals with the user interactions
     */

    String filename;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     *
     * @param file location of the file where data is stored
     * @throws DukeException when generating the tasks back from the data file
     * @throws IOException when the data file cannot be found
     */

    public MeowBot(String file) throws DukeException, IOException {
        this.ui = new Ui();
        this.storage = new Storage(file);
        this.filename = file;
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
     *
     * @throws DukeException when the command is not valid or has wrong parameters
     */

    public void run() throws DukeException {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();// show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            }  catch (DukeException e){
                System.out.println(e);
            }
        }
        ui.bye();
    }

    public static void main(String[] args) throws DukeException, IOException {
        new MeowBot("src/main/data/meowbot.txt").run();
    }

}
