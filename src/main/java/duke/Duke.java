package duke;

import java.util.Scanner;

//CHECKSTYLE.OFF: MissingJavadocMethodCheck
//CHECKSTYLE.OFF: MissingJavadocType
public class Duke {
    private Storage storage;
    private Launcher.TaskList tasks;
    private Ui ui;
    private Gui gui;
    private String filePath;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.filePath = filePath;
        try {
            tasks = new Launcher.TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            tasks = new Launcher.TaskList();
            this.filePath = filePath;
        }
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public void getDukeResponse(String userInput) {
        try {
            int number = tasks.num();
            Parser.parseInput(userInput, tasks, number, filePath, gui, storage);
        } catch (DukeException e) {
            gui.printError(e.getMessage());
        }
    }
}
