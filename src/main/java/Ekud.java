import extensions.*;

import java.util.Scanner;

public class Ekud {
    // TaskList object to store and manipulate all of user's tasks, as well as
    // handle invalid inputs
    private final TaskList taskList;
    private final Storage storage;
    public Ekud(Storage storage) {
        this.taskList = new TaskList();
        this.storage = storage;
    }
    public void start() throws EkudIOException {
        // Load up saved data
        this.storage.loadData(this.taskList);
        Scanner scanner = new Scanner(System.in);
        Ui.intro();
        // Process user input
        Parser parser = new Parser();
        String userInput = scanner.nextLine();
        String[] parsedInput = parser.parseInput(userInput);
        String userCommand = parsedInput[0];
        String userArgs = parsedInput[1];
        // Main chatbot functionality
        while (!userCommand.equals("end")) {
            try {
                parser.parseAndExecute(userCommand, userArgs, this.taskList); // throws EkudException for invalid inputs
            } catch(EkudException e) {
                Ui.printMsg(e.toString()); // catch and print out EkudException message
            }
            // Process next line of user input
            userInput = scanner.nextLine();
            parsedInput = parser.parseInput(userInput);
            userCommand = parsedInput[0];
            userArgs = parsedInput[1];
        }
        // Update saved tasks before exiting the program
        Ui.printMsg("Saving tasks...");
        this.storage.saveData(this.taskList);
        Ui.outro();
        scanner.close();

    }
    // Main chatbot program
    public static void main(String[] args) {
        try {
            Storage storage = new Storage("data/savedTasks.txt");
            new Ekud(storage).start();
        } catch(EkudIOException e) {
            System.out.println(e);
        }
    }
}
