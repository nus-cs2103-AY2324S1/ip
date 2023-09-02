package phi;
import java.util.Scanner;

/**
 * Represents the core of the PHI chatbot. The root of the program will run from here.
 *
 * @author phiphi-tan
 * @version 1.0.0
 */
public class Phi {
    private final Ui phiUi;
    private final Storage taskStorage;
    private final TaskList tasks;
    private final Parser phiParser;

    /**
     * Constructor for a new instance of Phi
     *
     * @param filePath the file path of the .txt file to be loaded from storage
     */
    public Phi(String filePath) {
        phiUi = new Ui();
        taskStorage = new Storage(filePath);
        tasks = taskStorage.readFromFile();
        phiParser = new Parser(tasks);
    }

    public static void main(String[] args) {
        new Phi("./data/tasklist.txt").run();
    }

    private void run() {
        phiUi.greeting();
        takeInput();
        phiUi.goodbye();
    }

    /**
     * Abstraction for the user-input handling process, which will continue running until the input "bye" is received
     */
    private void takeInput() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().trim();
        while (!input.equals("bye")) {
            System.out.println(phiParser.handle(input));
            taskStorage.writeToFile(tasks);
            System.out.println();
            input = sc.nextLine().trim();
        }
        sc.close();
    }
}