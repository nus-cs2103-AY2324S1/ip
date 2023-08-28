import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;
    private Parser parser;

    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.parser = new Parser(this.storage);
        this.ui = new Ui();
        try {
            // make use of this storage info to make a new TaskList
             this.taskList = storage.readFile(this.ui);
        } catch (IOException e) {
            // this error stems from error during creation of file
            System.out.println(e);
        }
    }

    private void run() {
        ui.printGreet();
        boolean ongoing = true;
        while (ongoing) {
            String userInput = ui.getUserInput();
            ongoing = parser.parse(userInput, taskList);
        }
        ui.printExit();
    }

    public static void main(String[] args) throws IOException{
        Duke main = new Duke("data/duke.txt");
        main.run();
    }
}
