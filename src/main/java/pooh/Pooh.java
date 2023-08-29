package pooh;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pooh {

    private final Storage taskStorage;
    private final Parser cmdParser;
    private TaskList taskList;

    public Pooh(String filePath) {

        cmdParser = new Parser();
        taskStorage = new Storage(filePath);
        try {
            taskList = new TaskList(taskStorage.loadTasks());
        } catch (LoadTasksException ex) {
            Ui.generalRespond(ex.toString());
            List<Task> listOfTasks = new ArrayList<Task>();
            taskList = new TaskList(listOfTasks);
        }
    }

    public void run() {
        Ui.welcomeMsg();
        Scanner userInput = new Scanner(System.in);
        while (userInput.hasNextLine()) {
            try {
                String userCmd = userInput.nextLine();
                cmdParser.parseInput(taskStorage, taskList, userCmd);
            } catch (UnrecognizedCommandException ex) {
                Ui.generalRespond(ex.toString());
            } catch (WriteTasksException ex) {
                Ui.generalRespond(ex.toString());
            } catch (InvalidTaskException ex) {
                Ui.generalRespond(ex.toString());
            }
        }
    }

    public static void main(String[] args) {
        new Pooh("pooh.txt").run();
    }
}