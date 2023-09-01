
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private Ui ui;
    private Storage storage;
    private TaskList tasklist;
    ArrayList<Task> taskArrayList = new ArrayList<>();
    private Parser parser;


    private Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.tasklist = new TaskList(taskArrayList);
        this.parser = null;

    }


    public static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    public void run() {
        ui.chadGreet();
        storage.makeNewDirectory();
        storage.makeNewFile();
        storage.loadFile(taskArrayList);
        parser = new Parser(this, taskArrayList);

        Scanner scanObj = new Scanner(System.in);
        boolean check = true;

        while (check) {
            String input = scanObj.nextLine();
            String[] inputArray = input.split(" ", 2);
            check = parser.inputParse(inputArray);
        }
        scanObj.close();
    }

        public static void main (String[]args){
            Duke chad = new Duke();
            chad.run();
        }


}
