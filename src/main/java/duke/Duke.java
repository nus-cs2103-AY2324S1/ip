package duke;

import java.io.FileNotFoundException;
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static final String FILE_PATH = "./data/duke.txt";

    public Duke() {}

    public String getResponse(String input) {
        return "Duke heard: " + input;
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadPreviousTasks());
        } catch (FileNotFoundException e) {
            ui.displayLoadErrorMessage(e.getMessage());
            taskList = new TaskList();
        }
    }

    public void run() {
        ui.displayWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserInput();
            try {
                isExit = Parser.userCommand(userInput, taskList, ui, storage);
            } catch (DukeException e) {
                ui.displayErrorMessage(e.getMessage());
            }
        }
        ui.stopUserInput();
    }

    public static void main(String[] args) {
        new Duke(FILE_PATH).run();
    }


}
// A-Gradle
// A-JUnit
// A-Jar
// A-JavaDoc
// A-CodingStandard
// Level-9
// Level-10
// A-FullCommitMessage
// A-Assertions
// A-CodeQuality
// A-UserGuide



