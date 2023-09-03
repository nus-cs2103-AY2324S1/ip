import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Ui ui;

    public Duke(String dataPath) {
        this.storage = new Storage(dataPath);
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        try {
            storage.readFile(taskList);
        } catch (FileNotFoundException e) {
            File dir = new File("./data");
            dir.mkdir();
            File dataFile = new File ("./data/duke.txt");
            try {
                dataFile.createNewFile();
                System.out.println("    Created new data file");
            } catch (IOException ioe) {
                System.out.println("    " + ioe.getMessage());
            }
        }
    }

    public void run() {
        ui.hello();
        Scanner in = new Scanner(System.in);
        String inputStr;
        do {
            inputStr = in.nextLine();
            ui.printHorizontalLine();
            try {
                parser.processCommand(inputStr, ui, taskList, storage);
            } catch (DukeException e) {
                ui.printError(e);
            }
            ui.printHorizontalLine();
        } while (!inputStr.equals("bye"));
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
