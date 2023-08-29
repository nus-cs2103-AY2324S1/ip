import duck.Parser;
import duck.Storage;
import duck.task.TaskList;
import ui.Ui;
import duck.IOHandler;

import exceptions.DuckException;

public class Duck {
    private Ui ui;
    private final IOHandler ioHandler = new IOHandler();
    private final TaskList taskList;
    private Parser parser = new Parser();
    private final Storage storage;

    public Duck(String filePath) {
        ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = new TaskList(storage.load());
    }

    public void run() {
        ioHandler.welcomeMessage();
        boolean exit = false;
        while (!exit) {
            exit = !parser.parse(ioHandler.typeMessage(), ioHandler, taskList, storage);
        }
    }

    public static void main(String[] args) {
        new Duck("data/duck.txt").run();
    }
}
