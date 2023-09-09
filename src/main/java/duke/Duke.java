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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        if (Parser.parsable(input)) {
            if (this.outputPath.equals("")) {
                this.ui.emptyFilePath();
            }
            return Parser.parse(input, ui, this.tasks, this.data);
        }
        return this.ui.exit();
    }
}
