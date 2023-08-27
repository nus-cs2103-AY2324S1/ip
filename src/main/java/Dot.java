import commands.Command;
import errors.DotException;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.File;

public class Dot {
    // NOTE: in contrast to the example in the tutorial,
    //  our Storage class is fully static
    private final String dataFilePathname;
    private final TaskList dotTaskList;
    private final Ui userInterface;

    public Dot(String dataFilePathname, int maxSize) {
        this.dataFilePathname = dataFilePathname;
        this.dotTaskList = TaskList.taskListFromArrayList(maxSize,
                Storage.getTasks(new File(dataFilePathname)));
        this.userInterface = new Ui();
    }

    // Inspired by tutorial sheet
    // The organisation was adapted, thus a similar looking run()
    // However, the deeper implementation was not abstracted
    public void run() {
        Ui.welcome();

        while (true) {
            try {
                String input = userInterface.readNextLine();
                Command cmd = Parser.parseInputToCommand(input, dotTaskList, dataFilePathname);
                if (cmd.isTerminateComamnd()) {
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
        Dot dotInstance = new Dot("src/main/java/data/dot.txt", 100);
        dotInstance.run();
    }
}
