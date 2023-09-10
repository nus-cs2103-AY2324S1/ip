package duke;

import java.io.FileNotFoundException;

/**
 * Encapsulates the bot application.
 *
 * @author Donovan Chan Jia Jun
 */
public class Duke {

    private String outputPath;
    private Storage data;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs the bot object.
     *
     * @param filePath String represetation of the relative path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.data = new Storage(filePath);
        this.outputPath = filePath;
        try {
            this.tasks = new TaskList(this.data.loadOutputFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtains the response by notDuke based on the user input.
     *
     * @param input The string that the user has entered
     */
    public String getResponse(String input) {
        if (!Parser.parsable(input)) {
            return this.ui.exit();
        }
        if (this.outputPath.equals("")) {
            this.ui.emptyFilePath();
        }
        return Parser.parse(input, ui, this.tasks, this.data);
    }
}
