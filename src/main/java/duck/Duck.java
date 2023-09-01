package duck;

import duck.task.TaskList;
import duck.ui.Ui;

public class Duck {
    private Ui ui;
    private final IoHandler ioHandler = new IoHandler();
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
