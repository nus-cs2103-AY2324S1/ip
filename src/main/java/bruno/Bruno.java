package bruno;

import java.time.DateTimeException;

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
     * @param dirPath The path of the directory that will store the file.
     * @param fileName The name of the file where the tasks are being loaded, or read from.
     */
    public Bruno(String dirPath, String fileName) {
        ui = new UI();
        storage = new Storage(dirPath, fileName);
        try {
            tasks = new TaskList(storage, ui);
            storage.loadFile(tasks);
        } catch (BrunoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Runs the Bruno application, allowing the user to interact with the task management system
     */
    public String runBruno(String s) throws bruno.exceptions.BrunoException {
        Parser parser = new Parser(tasks);
        String flag = parser.parseInput(s);
        if (flag.equals("bye")) {
            return ui.displayBye();
        }
        return flag;
    }

    public String getResponse(String s) {
        try {
            return runBruno(s);
        } catch (BrunoException | DateTimeException e) {
            return e.getMessage();
        }
    }
}
