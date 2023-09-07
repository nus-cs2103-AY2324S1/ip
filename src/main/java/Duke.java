import java.util.Scanner;

import command.Command;
import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import parser.Parser;
import ui.Ui;

/**
 * A class that the chatbot program will run from.
 */
public class Duke {

    private TaskList userList;
    private final FileStorage fileStorage;
    private final Ui userInterface;

    /**
     * A constructor method to initialise the bot.
     *
     * @param filePath the file that will be written or read from.
     */
    public Duke(String filePath) {
        this.userInterface = new Ui();
        this.fileStorage = new FileStorage(filePath);
        try {
            //System.out.println("here");
            userList = new TaskList(fileStorage.read());
        } catch (DukeException e) {
            //System.out.println("new userlist");
            this.userList = new TaskList();
            System.out.println("File Empty");
        }
    }

    /**
     * A method that will need the user to input what Text file they would like to use.
     *
     * @param args arguments use to start the program.
     */
    public static void main(String[] args) {
        System.out.println("\n \n" + "Please Input the txt file you wish to access");
        Scanner scanner = new Scanner(System.in);
        String textFile = scanner.nextLine();
        new Duke(textFile).run();
    }

    /**
     * A method to run the program.
     */
    public void run() {
        userInterface.showGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = userInterface.readCommand();
                userInterface.showLine();
                Command c = Parser.parse(fullCommand);
                c.excute(userList, userInterface, fileStorage);
                isExit = c.isExit();
            } catch (DukeException e) {
                userInterface.showError(e.getMessage());
            } finally {
                userInterface.showLine();
            }
        }
        userInterface.closeScanner();
    }
}

