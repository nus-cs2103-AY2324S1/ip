package duke;

import java.util.Scanner;

import duke.components.FileStorage;
import duke.components.Parser;
import duke.components.TaskList;
import duke.components.Ui;
import duke.exception.FileCorruptedException;
import duke.exception.FileLoadException;
import duke.exception.FileNoExistingTasksException;

/**
 * Represents the main class where the chatbot will run.
 */
public class Duke {
    private static String filePath = "./data/duke.txt";
    private Scanner scanner = new Scanner(System.in);
    private TaskList fullList;
    private final FileStorage fileStorage;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a chatBot which will load any existing tasks stored in the filePath
     * specified.
     *
     */
    public Duke() {
        this.fileStorage = new FileStorage(filePath);
        try {
            this.fullList = new TaskList(fileStorage.loadFiles());
            this.ui = new Ui();
            this.parser = new Parser(fileStorage, fullList, ui);
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

    public String getResponse(String input) {
        return parser.createTaskAction(input);
    }

    public String getWelcome() {
        return ui.showWelcome();
    }

}
