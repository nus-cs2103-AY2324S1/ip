package duke;

import command.UserInterface;
import java.util.Scanner;



/**
 * Duke is main class that controls the whole flow of the chatbot which makes of other classes like
 * Storage, TaskManager, UserInterface.
 */
public class Duke {

    private Storage storage;
    private TaskManager taskManager;
    private UserInterface userInterface;

    /**
     * Constructor which instantiates a new Duke.
     *
     */
    public Duke() {
        //Default constructor
        userInterface = new UserInterface();
        storage = new Storage("data/duke.txt");
        taskManager = new TaskManager(storage.load());
    }
    /**
     * Run method is where the whole chatbot starts from loading the file to saving changes to the file.
     */
    public void run() {
        userInterface.showWelcomeMessage();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while(!command.equals("bye")) {
            Parser.parseCommand(command, taskManager, userInterface, storage);
            command = sc.nextLine();
        }

        userInterface.showGoodbyeMessage();
        userInterface.showCommandLine();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return "Nila: \n" + Parser.parseCommand(input, taskManager, userInterface, storage);
    }



    /**
     * main method which is the entry point of chatbot.
     *
     * @param args the input arguments
     */
//    public static void main(String[] args) {
//        new Duke("data/duke.txt").run();
//    }


}


