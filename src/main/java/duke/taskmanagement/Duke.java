package duke.taskmanagement;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Duke {
    String PATH = "./data/duke.txt";

    private Ui ui = new Ui();
    //private Storage storage = new Storage(PATH);
    private TaskList tasks;
    private Storage storage = new Storage(PATH);


    public void run() {
        ui.greet();
        tasks = new TaskList(this.ui, storage.loadData(), storage);
        String cmd = this.ui.readInCmd();
        Parser parser = new Parser(this.ui, this.tasks);
        parser.readCmd(this.tasks, cmd);
    }
    public static void main(String[] args) {
        new Duke().run();
    }
}
