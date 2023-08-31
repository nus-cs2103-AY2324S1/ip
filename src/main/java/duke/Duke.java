package duke;

import java.util.Scanner;

/**
 * Represents the main class where the chatbot will run.
 */
public class Duke {
    private TaskList fullList;
    private final FileStorage FILE_STORAGE;
    private Parser parser;
    private static String filePath = "./data/duke.txt";
    private Ui ui;

    /**
     * Constructs a chatbot which will load any existing tasks stored in the filePath
     * specified.
     *
     * @param filePath Represents the location of stored tasks.
     */
    public Duke(String filePath) {
        this.FILE_STORAGE = new FileStorage(filePath);
        try {
            this.fullList  = new TaskList(FILE_STORAGE.loadFiles());
            this.ui = new Ui();
            this.parser = new Parser(FILE_STORAGE, fullList, ui);
        } catch (FileCorruptedException e) {
            System.out.println("Saved tasks is corrupted. Please start adding new tasks");
            this.fullList = new TaskList();
            this.ui = new Ui();
            this.parser = new Parser(filePath);
        } catch (FileNoExistingTasksException e) {
            System.out.println("No saved tasks! Please start adding new tasks");
            this.fullList = new TaskList();
            this.ui = new Ui();
            this.parser = new Parser(filePath);
        } catch (FileLoadException e) {
            System.out.println("Error when reading saved tasks. Please start adding new tasks");
            this.fullList = new TaskList();
            this.ui = new Ui();
            this.parser = new Parser(filePath);
        }
    }

    /**
     * Initialises the application, displays a welcome message and
     * enters a loop to process user inputs until the use enters "bye".
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isNotBye = true;
        while (isNotBye) {
            isNotBye = parser.createTaskAction(scanner.nextLine());
        }
        scanner.close();
    }

    /**
     * Entry point of the Duke application.
     *
     * @param args Command-line arguments. Not used in this context.
     */
    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
