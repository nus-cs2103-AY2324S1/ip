package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import duke.task.*;
import duke.helper.*;
import duke.command.*;
public class MeowBot {

    enum TaskType {Event, Deadline, Todo};
    String lines, filename;
    FileWriter writer;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
