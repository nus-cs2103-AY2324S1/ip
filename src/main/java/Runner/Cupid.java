package Runner;

import functions.Parser;
import functions.Storage;
import functions.TaskList;
import functions.Ui;

import java.io.IOException;
import java.util.Scanner;

public class Cupid {

    private String saveFilePath;
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Cupid(String saveFilePath) {
        this.saveFilePath = saveFilePath;
        this.ui = new Ui();
        this.taskList = null;
        this.storage = null;

        try {
            this.storage = new Storage(this.saveFilePath);
            this.taskList = this.storage.load();
        } catch (IOException e) {
            this.ui.fileNotFound();
        }

        if (this.taskList == null) {
            this.taskList = new TaskList();
            this.storage.save(this.taskList);
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        this.ui.hello();

        while (true) {
            String input = scanner.nextLine();

            if (input.toLowerCase().equals("bye")) {
                break;
            }
            Parser parser = new Parser(input, this.taskList);
            String result = parser.parse();
            System.out.println(result);

            this.storage.save(this.taskList);
        }
        ui.goodbye();
    }

    public String getResponse(String input) {
        if (input.toLowerCase().equals("bye")) {
            return ui.goodbye();
        }
        Parser parser = new Parser(input, this.taskList);
        String result = parser.parse();

        this.storage.save(this.taskList);
        return result;
    }

    public static void main(String[] args) throws IOException {
        new Cupid("cupid.txt").run();
    }
}
