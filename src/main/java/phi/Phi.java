package phi;
import java.util.Scanner;
import javafx.fxml.FXML;
import phi.util.Parser;
import phi.ui.Ui;
import phi.util.Storage;
import phi.util.TaskList;


/**
 * Represents the core of the PHI chatbot. The root of the program will run from here.
 *
 * @author phiphi-tan
 */
public class Phi  {
    private final Ui ui;
    private final Storage taskStorage;
    private final TaskList tasks;
    private final Parser parser;

    /**
     * Constructor for a new instance of Phi
     * Storage is loaded with the hardcoded file path of the .txt file
     */
    public Phi() {
        this.ui = new Ui();
        taskStorage = new Storage("./data/tasklist.txt");
        tasks = taskStorage.readFromFile();
        this.parser = new Parser(tasks);
    }
  
    /**
     * Abstraction for the user-input handling process, which will continue running until the input "bye" is received
     */
    private void takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals("bye")) {
            System.out.println(this.parser.handle(input));
            taskStorage.writeToFile(tasks);
            System.out.println();
            input = sc.nextLine().trim();
        }
        sc.close();
    }

    /**
     * Generates response to user input, and writes data to file after
     */
    @FXML
    public String getResponse(String input) {
        String response = this.parser.handle(input.trim());
        taskStorage.writeToFile(tasks);
        return response;
    }

}

