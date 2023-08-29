
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
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
        } catch (FileNotFoundException e) {
//            System.out.println("Meow?? I cant find your data");
           System.out.println("Meow???? I cant find your data");
           storage.createNewFile();

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
//        MeowBot meowBot = new MeowBot();
//        meowBot.greet();
//        meowBot.processCommand();
//        meowBot.bye();
        new MeowBot("src/main/data/meowbot.txt").run();
    }

}
