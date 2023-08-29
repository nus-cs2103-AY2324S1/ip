package duke;
import java.util.Scanner;
public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList toDo;

    public Duke(String filepath) {
        ui = new Ui();
        storage = new Storage(filepath);
        toDo = new TaskList();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        String userCommand;
        storage.load();
        ui.helloMsg();
        userCommand = sc.nextLine();

        while (!userCommand.equals("bye")) {
            Parser p = new Parser(userCommand);
            p.parseAndRespond();
            userCommand = sc.nextLine();
        }

        ui.goodbyeMsg();
    }
    public static void main(String[] args) {
        Duke dukey = new Duke("duke.txt");
        dukey.run();
    }
}
