package duke;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Donovan Chan Jia Jun
 */
public class Duke {
    /**
     * Temporary data storage to store user text.
     */
    static final String DIR = "/data";
    private String outputPath;
    private Storage data;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.data = new Storage(filePath);
        this.outputPath = filePath;
        try {
            this.tasks = new TaskList(this.data.LoadOutputFile());
        } catch (FileNotFoundException e) {

        }
    }
    /**
     * Starting point of the bot.
     * Says hello - Carries out data storage for user text - Says goodbye
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        Duke bot = new Duke(System.getProperty("user.dir") + DIR + "/ipOutput.txt");
        bot.run();
        bot.ui.exit();
    }

    /**
     * Starts the chatbot sequence.
     * When arraylist changes, the entire output file is overwritten and all contents is transferred over
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (Parser.parsable(userInput)) {
            if (this.outputPath.equals("")) {
                this.ui.emptyFilePath();
            }
            Parser.parse(userInput, ui, this.tasks, this.data);
            this.ui.createLine();
            userInput = scanner.nextLine();
        }
    }
}
