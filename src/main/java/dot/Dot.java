import commands.Command;
import errors.DotException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

public class Dot {
    private TaskList dotTaskList;
    private final Ui userInterface;
    private final Storage storage;

    public Dot(int maxSize) {
        String storageLocation = "./data/dot.txt";
        this.storage = new Storage(storageLocation);
        try {
            this.dotTaskList = TaskList.taskListFromArrayList(maxSize,
                    this.storage.getTasks(), this.storage);
        } catch (DotException e) {
            e.handleError();
            this.dotTaskList = TaskList.newTaskList(100, this.storage);
        }
        this.userInterface = new Ui();
    }

    // Inspired by tutorial sheet
    // The organisation was adapted, thus a similar looking run()
    // However, the deeper implementation were not adapted
    public void run() {
        Ui.welcome();

        while (true) {
            try {
                String input = userInterface.readNextLine();
                Command cmd = Parser.parseInputToCommand(input, dotTaskList);
                if (cmd.isTerminateCommand()) {
                    break;
                }
                cmd.execute();
            } catch (DotException e) {
                // For checked exception
                e.handleError();
            }
        }
        Ui.goodbye();
    }
    public static void main(String[] args) {
        Dot dotInstance;
        // DEPRECATED: If args[0] == "test", we enter Dot into testing mode
        dotInstance = new Dot(100);
        dotInstance.run();
    }
}
