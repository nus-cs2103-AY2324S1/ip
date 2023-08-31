package bruno;

import java.time.DateTimeException;
import java.util.Scanner;

import bruno.exceptions.BrunoException;

/**
 * The Bruno class represents the main application class that handles the running of the application.
 */
public class Bruno {

    private Storage storage;
    private TaskList tasks;
    private UI ui;

    /**
     * Creates a new instance of the Bruno class with the specified directory path and file name
     *
     * @param dirPath  The path of the directory that contains the 'bruno.txt' file.
     * @param fileName The name of the file that is loaded, and written to.
     */
    public Bruno(String dirPath, String fileName) {
        ui = new UI();
        storage = new Storage(dirPath, fileName);
        try {
            tasks = new TaskList(storage, ui);
            storage.loadFile();
        } catch (BrunoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the Bruno application, allowing the user to interact with the task management system
     */
    public void runBruno() {
        Parser parser = new Parser(tasks);
        Scanner sc = new Scanner(System.in);
        ui.displayLines();
        ui.displayGreeting();
        ui.displayLines();
        do {
            try {
                String s = sc.nextLine();
                ui.displayLines();
                boolean flag = parser.parseInput(s);
                if (!flag) {
                    ui.displayBye();
                    ui.displayLines();
                    System.exit(0);
                }
                ui.displayLines();
            } catch (BrunoException e) {
                System.out.println(e.getMessage());
                ui.displayLines();
            } catch (DateTimeException e) {
                System.out.println("\tDate and Time is not in correct format.");
                ui.displayLines();
            }
        } while (true);
    }

    public static void main(String[] args) {
        Bruno bruno = new Bruno("data/", "bruno.txt");
        bruno.runBruno();
    }
}
