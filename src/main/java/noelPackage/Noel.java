package noelPackage;

import noelPackage.exceptions.NoelException;
import noelPackage.helper.Parser;
import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;
import noelPackage.helper.Ui;

public class Noel {

    static String HELLO_MSG = " Hello! I'm Noel!\nWhat can I do for you?";
    static String BYE_MSG = " Bye. Hope to see you again soon!";
    private final Ui ui;
    private Tasklist tasks;
    private final Parser parser;


    public Noel(String filePath) {
        this.ui = new Ui();
        Storage storage = new Storage(filePath);
        try {
            tasks = new Tasklist(storage.loadFile());
        } catch (NoelException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        } finally {
            storage.updateFile();
            parser = new Parser(tasks, storage);
        }
    }

    public String chatHelper() {
        return this.ui.getNextLine();
    }

    public static void printFunction(String message){
        String filler = "____________________________________________________________";
        System.out.println(filler + "\n" + message + "\n" + filler);
    }

    public void run() {
        printFunction(HELLO_MSG);
        String nextLine = chatHelper();
        int commandOption = parser.parseCommand(nextLine);
        while (commandOption != 1) {
            nextLine = ui.getNextLine();
            commandOption = parser.parseCommand(nextLine);
        }
        printFunction(BYE_MSG);
    }

    public static void main(String[] args) {
        new Noel("./data/noel.txt").run();
    }
}

