import task.TaskList;
import util.Parser;
import util.Storage;
import util.Ui;

import java.util.Scanner;

public class EpochMind {

    public static void main(String[] args) {
        Ui.line();
        Ui.greeting();
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        Storage storage = new Storage();
        Parser parser = new Parser(tasks, storage);


        while (sc.hasNextLine()) {
            Ui.line();
            String command = sc.nextLine();
            parser.execute(command);
            Ui.line();
            storage.save(tasks);
        }
    }
}
