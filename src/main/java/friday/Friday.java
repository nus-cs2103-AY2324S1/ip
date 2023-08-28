package friday;

import java.util.Scanner;

/**
 * Represents the main class for the Friday application.
 */
public class Friday {

    private TaskList taskList;
    private Storage storage;
    private Scanner input;
    private Ui ui;

    private Parser parser;

    public Friday(String filePath) {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.input = new Scanner(System.in);
        this.storage = new Storage(filePath);
        this.parser = new Parser();
    }

    public static void main(String[] args) {
        Friday friday = new Friday("data/tasks.txt");
        friday.start();
    }

    public void start() {
        ui.greeting();
        parser.echo(input, taskList, ui, storage);
    }


}
