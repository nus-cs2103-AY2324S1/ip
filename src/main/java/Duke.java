import java.util.Scanner;

/**
 * Main class of Duke bot.
 * This class performs simple input and output handling and calls
 * appropriate functions from other classes.
 */
public class Duke {

    /** The storage object that handles storing of user data. */
    private Storage storage;

    /** The taskList object representing the list of tasks. */
    private TaskList taskList;

    /** String representing filepath of data file. */
    private String filePath;
    /**
     * Draws a line separating each conversation.
     *
     */
    public static void drawLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Initialises filepath field to given parameter.
     *
     * @param filePath String representing filepath of datafile.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Handles input from the user accordingly.
     *
     */
    public void run() {

        try {
            this.storage = new Storage(this.filePath);
            this.taskList = new TaskList(this.storage.load());
        } catch (DukeException d) {
            System.out.println("\t" + d.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        String userInput = scanner.nextLine();
        Duke.drawLine();
        Instruction instruction = null;
        while (true) {
            try {
                instruction = Parser.parse(userInput);

                instruction.execute(this.storage, this.taskList);

            } catch (DukeException d) {
                System.out.println("\t" + d.getMessage());

            }
            if (instruction instanceof Instruction.Exit) {
                return;
            }
            Duke.drawLine();
            System.out.println();
            userInput = scanner.nextLine();

            drawLine();
        }
    }

    /**
     * Greets and calls run and finally says goodbye.
     *
     * @param args Not used currently.
     */
    public static void main(String[] args) {

        String filePath = "./data/src/Duke.txt";
        drawLine();
        System.out.println("\tHello I am Vishnu.");
        System.out.println("\tWhat can I do for you?");
        drawLine();
        Duke duke = new Duke(filePath);
        duke.run();
        System.out.println("\tBye. Hope to see you again soon!");
        drawLine();
    }
}
