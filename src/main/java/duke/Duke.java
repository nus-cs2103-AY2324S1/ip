package duke;

import java.util.Scanner;

/**
 * Represents the main class where the chatbot will run.
 */
public class Duke {
    private TaskList fullList;
    private final FileStorage fileStorage;
    private Parser parser;
    private static String filePath = "./data/duke.txt";
    private Ui ui;

    public Duke(String filePath) {
        this.fileStorage = new FileStorage(filePath);
        try {
            this.fullList  = new TaskList(fileStorage.loadFiles());
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

    public void run() {
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();
        boolean isNotBye = true;
        while (isNotBye) {
            isNotBye = parser.createTaskAction(scanner.nextLine());
        }
        scanner.close();
    }

    public static void main(String[] args) {
        new Duke(filePath).run();
    }
}
